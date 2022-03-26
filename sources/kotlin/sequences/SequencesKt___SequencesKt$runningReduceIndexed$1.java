package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: _Sequences.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1", f = "_Sequences.kt", i = {0, 0, 0}, l = {2202, 2206}, m = "invokeSuspend", n = {"$this$sequence", "iterator", "accumulator"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class SequencesKt___SequencesKt$runningReduceIndexed$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $operation;
    final /* synthetic */ Sequence $this_runningReduceIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$runningReduceIndexed$1(Sequence sequence, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_runningReduceIndexed = sequence;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        SequencesKt___SequencesKt$runningReduceIndexed$1 sequencesKt___SequencesKt$runningReduceIndexed$1 = new SequencesKt___SequencesKt$runningReduceIndexed$1(this.$this_runningReduceIndexed, this.$operation, completion);
        sequencesKt___SequencesKt$runningReduceIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningReduceIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningReduceIndexed$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0066  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x008d -> B:23:0x008e). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            switch(r1) {
                case 0: goto L_0x0032;
                case 1: goto L_0x0023;
                case 2: goto L_0x0011;
                default: goto L_0x0009;
            }
        L_0x0009:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0011:
            r1 = r10
            int r2 = r1.I$0
            java.lang.Object r3 = r1.L$2
            java.lang.Object r4 = r1.L$1
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r1.L$0
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x008e
        L_0x0023:
            r1 = r10
            java.lang.Object r2 = r1.L$2
            java.lang.Object r3 = r1.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r1.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x005b
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r10
            java.lang.Object r2 = r1.L$0
            r4 = r2
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.sequences.Sequence r2 = r1.$this_runningReduceIndexed
            java.util.Iterator r3 = r2.iterator()
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x0093
            java.lang.Object r2 = r3.next()
            r1.L$0 = r4
            r1.L$1 = r3
            r1.L$2 = r2
            r5 = 1
            r1.label = r5
            java.lang.Object r5 = r4.yield(r2, r1)
            if (r5 != r0) goto L_0x005b
            return r0
        L_0x005b:
            r5 = 1
            r9 = r4
            r4 = r3
            r3 = r5
            r5 = r9
        L_0x0060:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0093
            kotlin.jvm.functions.Function3 r6 = r1.$operation
            int r7 = r3 + 1
            if (r3 >= 0) goto L_0x006f
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x006f:
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            java.lang.Object r8 = r4.next()
            java.lang.Object r3 = r6.invoke(r3, r2, r8)
            r1.L$0 = r5
            r1.L$1 = r4
            r1.L$2 = r3
            r1.I$0 = r7
            r2 = 2
            r1.label = r2
            java.lang.Object r2 = r5.yield(r3, r1)
            if (r2 != r0) goto L_0x008d
            return r0
        L_0x008d:
            r2 = r7
        L_0x008e:
            r9 = r3
            r3 = r2
            r2 = r9
            goto L_0x0060
        L_0x0093:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
