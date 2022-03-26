package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class AbstractResolvableFuture<V> implements ListenableFuture<V> {
    static final AtomicHelper ATOMIC_HELPER;
    private static final Object NULL;
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    volatile Listener listeners;
    volatile Object value;
    volatile Waiter waiters;
    static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger log = Logger.getLogger(AbstractResolvableFuture.class.getName());

    static {
        SafeAtomicHelper helper;
        Throwable thrownAtomicReferenceFieldUpdaterFailure = null;
        try {
            helper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Waiter.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Object.class, "value"));
        } catch (Throwable atomicReferenceFieldUpdaterFailure) {
            thrownAtomicReferenceFieldUpdaterFailure = atomicReferenceFieldUpdaterFailure;
            helper = new SynchronizedHelper();
        }
        ATOMIC_HELPER = helper;
        if (thrownAtomicReferenceFieldUpdaterFailure != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", thrownAtomicReferenceFieldUpdaterFailure);
        }
        NULL = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Waiter {
        static final Waiter TOMBSTONE = new Waiter(false);
        volatile Waiter next;
        volatile Thread thread;

        Waiter(boolean unused) {
        }

        Waiter() {
            AbstractResolvableFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }

        void setNext(Waiter next) {
            AbstractResolvableFuture.ATOMIC_HELPER.putNext(this, next);
        }

        void unpark() {
            Thread w = this.thread;
            if (w != null) {
                this.thread = null;
                LockSupport.unpark(w);
            }
        }
    }

    private void removeWaiter(Waiter node) {
        node.thread = null;
        while (true) {
            Waiter pred = null;
            Waiter curr = this.waiters;
            if (curr != Waiter.TOMBSTONE) {
                while (curr != null) {
                    Waiter succ = curr.next;
                    if (curr.thread != null) {
                        pred = curr;
                    } else if (pred != null) {
                        pred.next = succ;
                        if (pred.thread == null) {
                            break;
                        }
                    } else if (!ATOMIC_HELPER.casWaiters(this, curr, succ)) {
                        break;
                    }
                    curr = succ;
                }
                return;
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Listener {
        static final Listener TOMBSTONE = new Listener(null, null);
        final Executor executor;
        Listener next;
        final Runnable task;

        Listener(Runnable task, Executor executor) {
            this.task = task;
            this.executor = executor;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Failure {
        static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") { // from class: androidx.concurrent.futures.AbstractResolvableFuture.Failure.1
            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable exception;

        Failure(Throwable exception) {
            this.exception = (Throwable) AbstractResolvableFuture.checkNotNull(exception);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED;
        static final Cancellation CAUSELESS_INTERRUPTED;
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if (AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES) {
                CAUSELESS_CANCELLED = null;
                CAUSELESS_INTERRUPTED = null;
                return;
            }
            CAUSELESS_CANCELLED = new Cancellation(false, null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, null);
        }

        Cancellation(boolean wasInterrupted, Throwable cause) {
            this.wasInterrupted = wasInterrupted;
            this.cause = cause;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class SetFuture<V> implements Runnable {
        final ListenableFuture<? extends V> future;
        final AbstractResolvableFuture<V> owner;

        SetFuture(AbstractResolvableFuture<V> owner, ListenableFuture<? extends V> future) {
            this.owner = owner;
            this.future = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.owner.value == this) {
                Object valueToSet = AbstractResolvableFuture.getFutureValue(this.future);
                if (AbstractResolvableFuture.ATOMIC_HELPER.casValue(this.owner, this, valueToSet)) {
                    AbstractResolvableFuture.complete(this.owner);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
        java.util.concurrent.locks.LockSupport.parkNanos(r28, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        if (java.lang.Thread.interrupted() != false) goto L_0x007b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
        r8 = r28.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0058, code lost:
        if (r8 == null) goto L_0x005c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005a, code lost:
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005c, code lost:
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
        if ((r12 & (!(r8 instanceof androidx.concurrent.futures.AbstractResolvableFuture.SetFuture))) == false) goto L_0x0068;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0067, code lost:
        return getDoneValue(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        r6 = r13 - java.lang.System.nanoTime();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0072, code lost:
        if (r6 >= androidx.concurrent.futures.AbstractResolvableFuture.SPIN_THRESHOLD_NANOS) goto L_0x0078;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0074, code lost:
        removeWaiter(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007b, code lost:
        removeWaiter(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0083, code lost:
        throw new java.lang.InterruptedException();
     */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V get(long r29, java.util.concurrent.TimeUnit r31) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException, java.util.concurrent.ExecutionException {
        /*
            Method dump skipped, instructions count: 500
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.concurrent.futures.AbstractResolvableFuture.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
        java.util.concurrent.locks.LockSupport.park(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
        if (java.lang.Thread.interrupted() != false) goto L_0x004b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
        r0 = r7.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003b, code lost:
        if (r0 == null) goto L_0x003f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003d, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
        if ((r5 & (!(r0 instanceof androidx.concurrent.futures.AbstractResolvableFuture.SetFuture))) == false) goto L_0x0030;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004a, code lost:
        return getDoneValue(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        removeWaiter(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
        throw new java.lang.InterruptedException();
     */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V get() throws java.lang.InterruptedException, java.util.concurrent.ExecutionException {
        /*
            r7 = this;
            boolean r0 = java.lang.Thread.interrupted()
            if (r0 != 0) goto L_0x0061
            java.lang.Object r0 = r7.value
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000e
            r3 = r2
            goto L_0x000f
        L_0x000e:
            r3 = r1
        L_0x000f:
            boolean r4 = r0 instanceof androidx.concurrent.futures.AbstractResolvableFuture.SetFuture
            r4 = r4 ^ r2
            r3 = r3 & r4
            if (r3 == 0) goto L_0x001a
            java.lang.Object r1 = r7.getDoneValue(r0)
            return r1
        L_0x001a:
            androidx.concurrent.futures.AbstractResolvableFuture$Waiter r3 = r7.waiters
            androidx.concurrent.futures.AbstractResolvableFuture$Waiter r4 = androidx.concurrent.futures.AbstractResolvableFuture.Waiter.TOMBSTONE
            if (r3 == r4) goto L_0x005a
            androidx.concurrent.futures.AbstractResolvableFuture$Waiter r4 = new androidx.concurrent.futures.AbstractResolvableFuture$Waiter
            r4.<init>()
        L_0x0025:
            r4.setNext(r3)
            androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper r5 = androidx.concurrent.futures.AbstractResolvableFuture.ATOMIC_HELPER
            boolean r5 = r5.casWaiters(r7, r3, r4)
            if (r5 == 0) goto L_0x0054
        L_0x0030:
            java.util.concurrent.locks.LockSupport.park(r7)
            boolean r5 = java.lang.Thread.interrupted()
            if (r5 != 0) goto L_0x004b
            java.lang.Object r0 = r7.value
            if (r0 == 0) goto L_0x003f
            r5 = r2
            goto L_0x0040
        L_0x003f:
            r5 = r1
        L_0x0040:
            boolean r6 = r0 instanceof androidx.concurrent.futures.AbstractResolvableFuture.SetFuture
            r6 = r6 ^ r2
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0030
            java.lang.Object r1 = r7.getDoneValue(r0)
            return r1
        L_0x004b:
            r7.removeWaiter(r4)
            java.lang.InterruptedException r1 = new java.lang.InterruptedException
            r1.<init>()
            throw r1
        L_0x0054:
            androidx.concurrent.futures.AbstractResolvableFuture$Waiter r3 = r7.waiters
            androidx.concurrent.futures.AbstractResolvableFuture$Waiter r5 = androidx.concurrent.futures.AbstractResolvableFuture.Waiter.TOMBSTONE
            if (r3 != r5) goto L_0x0025
        L_0x005a:
            java.lang.Object r1 = r7.value
            java.lang.Object r1 = r7.getDoneValue(r1)
            return r1
        L_0x0061:
            java.lang.InterruptedException r0 = new java.lang.InterruptedException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.concurrent.futures.AbstractResolvableFuture.get():java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).cause);
        } else if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).exception);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object localValue = this.value;
        return (true ^ (localValue instanceof SetFuture)) & (localValue != null);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        Object localValue = this.value;
        return localValue instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean mayInterruptIfRunning) {
        Object localValue = this.value;
        boolean rValue = false;
        if ((localValue == null) || (localValue instanceof SetFuture)) {
            Object valueToSet = GENERATE_CANCELLATION_CAUSES ? new Cancellation(mayInterruptIfRunning, new CancellationException("Future.cancel() was called.")) : mayInterruptIfRunning ? Cancellation.CAUSELESS_INTERRUPTED : Cancellation.CAUSELESS_CANCELLED;
            AbstractResolvableFuture<V> abstractResolvableFuture = this;
            while (true) {
                if (ATOMIC_HELPER.casValue(abstractResolvableFuture, localValue, valueToSet)) {
                    rValue = true;
                    if (mayInterruptIfRunning) {
                        abstractResolvableFuture.interruptTask();
                    }
                    complete(abstractResolvableFuture);
                    if (!(localValue instanceof SetFuture)) {
                        break;
                    }
                    ListenableFuture<?> futureToPropagateTo = ((SetFuture) localValue).future;
                    if (!(futureToPropagateTo instanceof AbstractResolvableFuture)) {
                        futureToPropagateTo.cancel(mayInterruptIfRunning);
                        break;
                    }
                    AbstractResolvableFuture<V> abstractResolvableFuture2 = (AbstractResolvableFuture) futureToPropagateTo;
                    localValue = abstractResolvableFuture2.value;
                    if (!(localValue == null) && !(localValue instanceof SetFuture)) {
                        break;
                    }
                    abstractResolvableFuture = abstractResolvableFuture2;
                } else {
                    localValue = abstractResolvableFuture.value;
                    if (!(localValue instanceof SetFuture)) {
                        break;
                    }
                }
            }
        }
        return rValue;
    }

    protected void interruptTask() {
    }

    protected final boolean wasInterrupted() {
        Object localValue = this.value;
        return (localValue instanceof Cancellation) && ((Cancellation) localValue).wasInterrupted;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable listener, Executor executor) {
        checkNotNull(listener);
        checkNotNull(executor);
        Listener oldHead = this.listeners;
        if (oldHead != Listener.TOMBSTONE) {
            Listener newNode = new Listener(listener, executor);
            do {
                newNode.next = oldHead;
                if (!ATOMIC_HELPER.casListeners(this, oldHead, newNode)) {
                    oldHead = this.listeners;
                } else {
                    return;
                }
            } while (oldHead != Listener.TOMBSTONE);
            executeListener(listener, executor);
        }
        executeListener(listener, executor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean set(V value) {
        Object valueToSet = value == null ? NULL : value;
        if (!ATOMIC_HELPER.casValue(this, null, valueToSet)) {
            return false;
        }
        complete(this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setException(Throwable throwable) {
        Object valueToSet = new Failure((Throwable) checkNotNull(throwable));
        if (!ATOMIC_HELPER.casValue(this, null, valueToSet)) {
            return false;
        }
        complete(this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setFuture(ListenableFuture<? extends V> future) {
        Failure failure;
        checkNotNull(future);
        Object localValue = this.value;
        if (localValue == null) {
            if (future.isDone()) {
                Object value = getFutureValue(future);
                if (!ATOMIC_HELPER.casValue(this, null, value)) {
                    return false;
                }
                complete(this);
                return true;
            }
            SetFuture valueToSet = new SetFuture(this, future);
            if (ATOMIC_HELPER.casValue(this, null, valueToSet)) {
                try {
                    future.addListener(valueToSet, DirectExecutor.INSTANCE);
                } catch (Throwable t) {
                    try {
                        failure = new Failure(t);
                    } catch (Throwable th) {
                        failure = Failure.FALLBACK_INSTANCE;
                    }
                    ATOMIC_HELPER.casValue(this, valueToSet, failure);
                }
                return true;
            }
            localValue = this.value;
        }
        if (localValue instanceof Cancellation) {
            future.cancel(((Cancellation) localValue).wasInterrupted);
        }
        return false;
    }

    static Object getFutureValue(ListenableFuture<?> future) {
        if (future instanceof AbstractResolvableFuture) {
            Object v = ((AbstractResolvableFuture) future).value;
            if (!(v instanceof Cancellation)) {
                return v;
            }
            Cancellation c = (Cancellation) v;
            if (!c.wasInterrupted) {
                return v;
            }
            return c.cause != null ? new Cancellation(false, c.cause) : Cancellation.CAUSELESS_CANCELLED;
        }
        boolean wasCancelled = future.isCancelled();
        if ((!GENERATE_CANCELLATION_CAUSES) && wasCancelled) {
            return Cancellation.CAUSELESS_CANCELLED;
        }
        try {
            Object v2 = getUninterruptibly(future);
            return v2 == null ? NULL : v2;
        } catch (CancellationException cancellation) {
            if (!wasCancelled) {
                return new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + future, cancellation));
            }
            return new Cancellation(false, cancellation);
        } catch (ExecutionException exception) {
            return new Failure(exception.getCause());
        } catch (Throwable t) {
            return new Failure(t);
        }
    }

    private static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        boolean interrupted = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper] */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.concurrent.futures.AbstractResolvableFuture<?>] */
    /* JADX WARN: Type inference failed for: r6v1, types: [androidx.concurrent.futures.AbstractResolvableFuture] */
    /* JADX WARN: Type inference failed for: r6v3, types: [androidx.concurrent.futures.AbstractResolvableFuture, androidx.concurrent.futures.AbstractResolvableFuture<V>] */
    static void complete(AbstractResolvableFuture<?> abstractResolvableFuture) {
        Listener next = null;
        while (true) {
            abstractResolvableFuture.releaseWaiters();
            abstractResolvableFuture.afterDone();
            next = abstractResolvableFuture.clearListeners(next);
            while (next != null) {
                next = next.next;
                Runnable task = next.task;
                if (task instanceof SetFuture) {
                    SetFuture<?> setFuture = (SetFuture) task;
                    AbstractResolvableFuture<?> future = setFuture.owner;
                    abstractResolvableFuture = (AbstractResolvableFuture<V>) future;
                    if (abstractResolvableFuture.value == setFuture) {
                        Object valueToSet = getFutureValue(setFuture.future);
                        if (ATOMIC_HELPER.casValue(abstractResolvableFuture, setFuture, valueToSet)) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    executeListener(task, next.executor);
                }
            }
            return;
        }
    }

    protected void afterDone() {
    }

    final void maybePropagateCancellationTo(Future<?> related) {
        if ((related != null) && isCancelled()) {
            related.cancel(wasInterrupted());
        }
    }

    private void releaseWaiters() {
        Waiter head;
        do {
            head = this.waiters;
        } while (!ATOMIC_HELPER.casWaiters(this, head, Waiter.TOMBSTONE));
        for (Waiter currentWaiter = head; currentWaiter != null; currentWaiter = currentWaiter.next) {
            currentWaiter.unpark();
        }
    }

    private Listener clearListeners(Listener onto) {
        Listener head;
        do {
            head = this.listeners;
        } while (!ATOMIC_HELPER.casListeners(this, head, Listener.TOMBSTONE));
        Listener reversedList = onto;
        while (head != null) {
            head = head.next;
            head.next = reversedList;
            reversedList = head;
        }
        return reversedList;
    }

    public String toString() {
        String pendingDescription;
        StringBuilder builder = new StringBuilder().append(super.toString()).append("[status=");
        if (isCancelled()) {
            builder.append("CANCELLED");
        } else if (isDone()) {
            addDoneString(builder);
        } else {
            try {
                pendingDescription = pendingToString();
            } catch (RuntimeException e) {
                pendingDescription = "Exception thrown from implementation: " + e.getClass();
            }
            if (pendingDescription != null && !pendingDescription.isEmpty()) {
                builder.append("PENDING, info=[").append(pendingDescription).append("]");
            } else if (isDone()) {
                addDoneString(builder);
            } else {
                builder.append("PENDING");
            }
        }
        return builder.append("]").toString();
    }

    protected String pendingToString() {
        Object localValue = this.value;
        if (localValue instanceof SetFuture) {
            return "setFuture=[" + userObjectToString(((SetFuture) localValue).future) + "]";
        }
        if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }
        return null;
    }

    private void addDoneString(StringBuilder builder) {
        try {
            builder.append("SUCCESS, result=[").append(userObjectToString(getUninterruptibly(this))).append("]");
        } catch (CancellationException e) {
            builder.append("CANCELLED");
        } catch (RuntimeException e2) {
            builder.append("UNKNOWN, cause=[").append(e2.getClass()).append(" thrown from get()]");
        } catch (ExecutionException e3) {
            builder.append("FAILURE, cause=[").append(e3.getCause()).append("]");
        }
    }

    private String userObjectToString(Object o) {
        if (o == this) {
            return "this future";
        }
        return String.valueOf(o);
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class AtomicHelper {
        abstract boolean casListeners(AbstractResolvableFuture<?> abstractResolvableFuture, Listener listener, Listener listener2);

        abstract boolean casValue(AbstractResolvableFuture<?> abstractResolvableFuture, Object obj, Object obj2);

        abstract boolean casWaiters(AbstractResolvableFuture<?> abstractResolvableFuture, Waiter waiter, Waiter waiter2);

        abstract void putNext(Waiter waiter, Waiter waiter2);

        abstract void putThread(Waiter waiter, Thread thread);

        private AtomicHelper() {
        }
    }

    /* loaded from: classes.dex */
    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Listener> listenersUpdater;
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Waiter> waitersUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater, AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Waiter> waitersUpdater, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Listener> listenersUpdater, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> valueUpdater) {
            super();
            this.waiterThreadUpdater = waiterThreadUpdater;
            this.waiterNextUpdater = waiterNextUpdater;
            this.waitersUpdater = waitersUpdater;
            this.listenersUpdater = listenersUpdater;
            this.valueUpdater = valueUpdater;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void putThread(Waiter waiter, Thread newValue) {
            this.waiterThreadUpdater.lazySet(waiter, newValue);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void putNext(Waiter waiter, Waiter newValue) {
            this.waiterNextUpdater.lazySet(waiter, newValue);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean casWaiters(AbstractResolvableFuture<?> future, Waiter expect, Waiter update) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.waitersUpdater, future, expect, update);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean casListeners(AbstractResolvableFuture<?> future, Listener expect, Listener update) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.listenersUpdater, future, expect, update);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean casValue(AbstractResolvableFuture<?> future, Object expect, Object update) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(this.valueUpdater, future, expect, update);
        }
    }

    /* loaded from: classes.dex */
    private static final class SynchronizedHelper extends AtomicHelper {
        SynchronizedHelper() {
            super();
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void putThread(Waiter waiter, Thread newValue) {
            waiter.thread = newValue;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        void putNext(Waiter waiter, Waiter newValue) {
            waiter.next = newValue;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean casWaiters(AbstractResolvableFuture<?> future, Waiter expect, Waiter update) {
            synchronized (future) {
                if (future.waiters != expect) {
                    return false;
                }
                future.waiters = update;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean casListeners(AbstractResolvableFuture<?> future, Listener expect, Listener update) {
            synchronized (future) {
                if (future.listeners != expect) {
                    return false;
                }
                future.listeners = update;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AtomicHelper
        boolean casValue(AbstractResolvableFuture<?> future, Object expect, Object update) {
            synchronized (future) {
                if (future.value != expect) {
                    return false;
                }
                future.value = update;
                return true;
            }
        }
    }

    private static CancellationException cancellationExceptionWithCause(String message, Throwable cause) {
        CancellationException exception = new CancellationException(message);
        exception.initCause(cause);
        return exception;
    }

    static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }
}
