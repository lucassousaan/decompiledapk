package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: Sequences.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\t\u0010\u0013\u001a\u00020\u0014H\u0096\u0002J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"kotlin/sequences/DropWhileSequence$iterator$1", "", "dropState", "", "getDropState", "()I", "setDropState", "(I)V", "iterator", "getIterator", "()Ljava/util/Iterator;", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "drop", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class DropWhileSequence$iterator$1 implements Iterator<T>, KMappedMarker {
    private int dropState = -1;
    private final Iterator<T> iterator;
    private T nextItem;
    final /* synthetic */ DropWhileSequence this$0;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DropWhileSequence$iterator$1(DropWhileSequence this$0) {
        Sequence sequence;
        this.this$0 = this$0;
        sequence = this$0.sequence;
        this.iterator = sequence.iterator();
    }

    public final Iterator<T> getIterator() {
        return this.iterator;
    }

    public final int getDropState() {
        return this.dropState;
    }

    public final void setDropState(int i) {
        this.dropState = i;
    }

    public final T getNextItem() {
        return this.nextItem;
    }

    public final void setNextItem(T t) {
        this.nextItem = t;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void drop() {
        /*
            r2 = this;
        L_0x0000:
            java.util.Iterator<T> r0 = r2.iterator
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L_0x0027
            java.util.Iterator<T> r0 = r2.iterator
            java.lang.Object r0 = r0.next()
            kotlin.sequences.DropWhileSequence r1 = r2.this$0
            kotlin.jvm.functions.Function1 r1 = kotlin.sequences.DropWhileSequence.access$getPredicate$p(r1)
            java.lang.Object r1 = r1.invoke(r0)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0026
            r2.nextItem = r0
            r1 = 1
            r2.dropState = r1
            return
        L_0x0026:
            goto L_0x0000
        L_0x0027:
            r0 = 0
            r2.dropState = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.DropWhileSequence$iterator$1.drop():void");
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
    @Override // java.util.Iterator
    public T next() {
        if (this.dropState == -1) {
            drop();
        }
        if (this.dropState != 1) {
            return this.iterator.next();
        }
        T t = this.nextItem;
        this.nextItem = null;
        this.dropState = 0;
        return t;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.dropState == -1) {
            drop();
        }
        return this.dropState == 1 || this.iterator.hasNext();
    }
}
