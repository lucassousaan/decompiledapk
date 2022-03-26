package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _Sequences.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1", f = "_Sequences.kt", i = {0}, l = {2143, 2148}, m = "invokeSuspend", n = {"$this$sequence"}, s = {"L$0"})
/* loaded from: classes.dex */
public final class SequencesKt___SequencesKt$runningFoldIndexed$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initial;
    final /* synthetic */ Function3 $operation;
    final /* synthetic */ Sequence $this_runningFoldIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$runningFoldIndexed$1(Sequence sequence, Object obj, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_runningFoldIndexed = sequence;
        this.$initial = obj;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        SequencesKt___SequencesKt$runningFoldIndexed$1 sequencesKt___SequencesKt$runningFoldIndexed$1 = new SequencesKt___SequencesKt$runningFoldIndexed$1(this.$this_runningFoldIndexed, this.$initial, this.$operation, completion);
        sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningFoldIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningFoldIndexed$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SequenceScope $this$sequence;
        Object accumulator;
        Iterator it;
        int index;
        SequencesKt___SequencesKt$runningFoldIndexed$1 sequencesKt___SequencesKt$runningFoldIndexed$1;
        SequenceScope $this$sequence2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                sequencesKt___SequencesKt$runningFoldIndexed$1 = this;
                $this$sequence2 = (SequenceScope) sequencesKt___SequencesKt$runningFoldIndexed$1.L$0;
                Object obj = sequencesKt___SequencesKt$runningFoldIndexed$1.$initial;
                sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = $this$sequence2;
                sequencesKt___SequencesKt$runningFoldIndexed$1.label = 1;
                if ($this$sequence2.yield(obj, sequencesKt___SequencesKt$runningFoldIndexed$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                accumulator = sequencesKt___SequencesKt$runningFoldIndexed$1.$initial;
                it = sequencesKt___SequencesKt$runningFoldIndexed$1.$this_runningFoldIndexed.iterator();
                $this$sequence = $this$sequence2;
                index = 0;
                break;
            case 1:
                sequencesKt___SequencesKt$runningFoldIndexed$1 = this;
                $this$sequence2 = (SequenceScope) sequencesKt___SequencesKt$runningFoldIndexed$1.L$0;
                ResultKt.throwOnFailure($result);
                accumulator = sequencesKt___SequencesKt$runningFoldIndexed$1.$initial;
                it = sequencesKt___SequencesKt$runningFoldIndexed$1.$this_runningFoldIndexed.iterator();
                $this$sequence = $this$sequence2;
                index = 0;
                break;
            case 2:
                sequencesKt___SequencesKt$runningFoldIndexed$1 = this;
                index = sequencesKt___SequencesKt$runningFoldIndexed$1.I$0;
                it = (Iterator) sequencesKt___SequencesKt$runningFoldIndexed$1.L$2;
                accumulator = sequencesKt___SequencesKt$runningFoldIndexed$1.L$1;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningFoldIndexed$1.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object next = it.next();
            Function3 function3 = sequencesKt___SequencesKt$runningFoldIndexed$1.$operation;
            int i = index + 1;
            if (index < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            accumulator = function3.invoke(Boxing.boxInt(index), accumulator, next);
            sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = $this$sequence;
            sequencesKt___SequencesKt$runningFoldIndexed$1.L$1 = accumulator;
            sequencesKt___SequencesKt$runningFoldIndexed$1.L$2 = it;
            sequencesKt___SequencesKt$runningFoldIndexed$1.I$0 = i;
            sequencesKt___SequencesKt$runningFoldIndexed$1.label = 2;
            if ($this$sequence.yield(accumulator, sequencesKt___SequencesKt$runningFoldIndexed$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            index = i;
        }
        return Unit.INSTANCE;
    }
}
