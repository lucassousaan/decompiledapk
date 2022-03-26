package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Grouping.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u009e\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\bø\u0001\u0000\u001a·\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aI\u0010\u0014\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007¢\u0006\u0002\u0010\u0016\u001a¿\u0001\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\bø\u0001\u0000\u001a\u007f\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aØ\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u001026\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0093\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a\u008b\u0001\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0001\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\bø\u0001\u0000\u001a¤\u0001\u0010\"\u001a\u0002H\u0010\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"aggregate", "", "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCountTo", "", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"}, k = 5, mv = {1, 5, 1}, xi = 1, xs = "kotlin/collections/GroupingKt")
/* loaded from: classes.dex */
class GroupingKt__GroupingKt extends GroupingKt__GroupingJVMKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R> java.util.Map<K, R> aggregate(kotlin.collections.Grouping<T, ? extends K> r9, kotlin.jvm.functions.Function4<? super K, ? super R, ? super T, ? super java.lang.Boolean, ? extends R> r10) {
        /*
            r0 = 0
            java.lang.String r1 = "$this$aggregate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r1)
            java.lang.String r1 = "operation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r1)
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            java.util.Map r1 = (java.util.Map) r1
            r2 = r9
            r3 = 0
            java.util.Iterator r4 = r2.sourceIterator()
        L_0x0018:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0041
            java.lang.Object r5 = r4.next()
            java.lang.Object r6 = r2.keyOf(r5)
            java.lang.Object r7 = r1.get(r6)
            if (r7 != 0) goto L_0x0034
            boolean r8 = r1.containsKey(r6)
            if (r8 != 0) goto L_0x0034
            r8 = 1
            goto L_0x0035
        L_0x0034:
            r8 = 0
        L_0x0035:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            java.lang.Object r8 = r10.invoke(r6, r7, r5, r8)
            r1.put(r6, r8)
            goto L_0x0018
        L_0x0041:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt__GroupingKt.aggregate(kotlin.collections.Grouping, kotlin.jvm.functions.Function4):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R, M extends java.util.Map<? super K, R>> M aggregateTo(kotlin.collections.Grouping<T, ? extends K> r6, M r7, kotlin.jvm.functions.Function4<? super K, ? super R, ? super T, ? super java.lang.Boolean, ? extends R> r8) {
        /*
            r0 = 0
            java.lang.String r1 = "$this$aggregateTo"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            java.lang.String r1 = "destination"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            java.lang.String r1 = "operation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r1)
            java.util.Iterator r1 = r6.sourceIterator()
        L_0x0014:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x003d
            java.lang.Object r2 = r1.next()
            java.lang.Object r3 = r6.keyOf(r2)
            java.lang.Object r4 = r7.get(r3)
            if (r4 != 0) goto L_0x0030
            boolean r5 = r7.containsKey(r3)
            if (r5 != 0) goto L_0x0030
            r5 = 1
            goto L_0x0031
        L_0x0030:
            r5 = 0
        L_0x0031:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            java.lang.Object r5 = r8.invoke(r3, r4, r2, r5)
            r7.put(r3, r5)
            goto L_0x0014
        L_0x003d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt__GroupingKt.aggregateTo(kotlin.collections.Grouping, java.util.Map, kotlin.jvm.functions.Function4):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R> java.util.Map<K, R> fold(kotlin.collections.Grouping<T, ? extends K> r19, kotlin.jvm.functions.Function2<? super K, ? super T, ? extends R> r20, kotlin.jvm.functions.Function3<? super K, ? super R, ? super T, ? extends R> r21) {
        /*
            r0 = r20
            r1 = r21
            r2 = 0
            java.lang.String r3 = "$this$fold"
            r4 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
            java.lang.String r3 = "initialValueSelector"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = "operation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            r3 = r19
            r5 = 0
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
            java.util.Map r6 = (java.util.Map) r6
            r7 = r3
            r8 = 0
            java.util.Iterator r9 = r7.sourceIterator()
        L_0x0027:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0068
            java.lang.Object r10 = r9.next()
            java.lang.Object r11 = r7.keyOf(r10)
            java.lang.Object r12 = r6.get(r11)
            if (r12 != 0) goto L_0x0043
            boolean r13 = r6.containsKey(r11)
            if (r13 != 0) goto L_0x0043
            r13 = 1
            goto L_0x0044
        L_0x0043:
            r13 = 0
        L_0x0044:
            r14 = r12
            r15 = r10
            r16 = r11
            r17 = 0
            if (r13 == 0) goto L_0x0057
            r18 = r2
            r2 = r16
            java.lang.Object r16 = r0.invoke(r2, r15)
            r0 = r16
            goto L_0x005c
        L_0x0057:
            r18 = r2
            r2 = r16
            r0 = r14
        L_0x005c:
            java.lang.Object r0 = r1.invoke(r2, r0, r15)
            r6.put(r11, r0)
            r0 = r20
            r2 = r18
            goto L_0x0027
        L_0x0068:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt__GroupingKt.fold(kotlin.collections.Grouping, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R, M extends java.util.Map<? super K, R>> M foldTo(kotlin.collections.Grouping<T, ? extends K> r17, M r18, kotlin.jvm.functions.Function2<? super K, ? super T, ? extends R> r19, kotlin.jvm.functions.Function3<? super K, ? super R, ? super T, ? extends R> r20) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = 0
            java.lang.String r4 = "$this$foldTo"
            r5 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
            java.lang.String r4 = "destination"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "initialValueSelector"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "operation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            r4 = r17
            r6 = 0
            java.util.Iterator r7 = r4.sourceIterator()
        L_0x0025:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x005a
            java.lang.Object r8 = r7.next()
            java.lang.Object r9 = r4.keyOf(r8)
            java.lang.Object r10 = r0.get(r9)
            if (r10 != 0) goto L_0x0041
            boolean r11 = r0.containsKey(r9)
            if (r11 != 0) goto L_0x0041
            r11 = 1
            goto L_0x0042
        L_0x0041:
            r11 = 0
        L_0x0042:
            r12 = r9
            r13 = r8
            r14 = r10
            r15 = 0
            if (r11 == 0) goto L_0x004f
            java.lang.Object r16 = r1.invoke(r12, r13)
            r1 = r16
            goto L_0x0050
        L_0x004f:
            r1 = r14
        L_0x0050:
            java.lang.Object r1 = r2.invoke(r12, r1, r13)
            r0.put(r9, r1)
            r1 = r19
            goto L_0x0025
        L_0x005a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt__GroupingKt.foldTo(kotlin.collections.Grouping, java.util.Map, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R> java.util.Map<K, R> fold(kotlin.collections.Grouping<T, ? extends K> r18, R r19, kotlin.jvm.functions.Function2<? super R, ? super T, ? extends R> r20) {
        /*
            r0 = r20
            r1 = 0
            java.lang.String r2 = "$this$fold"
            r3 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            java.lang.String r2 = "operation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r2 = r18
            r4 = 0
            java.util.LinkedHashMap r5 = new java.util.LinkedHashMap
            r5.<init>()
            java.util.Map r5 = (java.util.Map) r5
            r6 = r2
            r7 = 0
            java.util.Iterator r8 = r6.sourceIterator()
        L_0x0020:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0054
            java.lang.Object r9 = r8.next()
            java.lang.Object r10 = r6.keyOf(r9)
            java.lang.Object r11 = r5.get(r10)
            if (r11 != 0) goto L_0x003c
            boolean r12 = r5.containsKey(r10)
            if (r12 != 0) goto L_0x003c
            r12 = 1
            goto L_0x003d
        L_0x003c:
            r12 = 0
        L_0x003d:
            r13 = r11
            r14 = r9
            r15 = r10
            r16 = 0
            r17 = r1
            if (r12 == 0) goto L_0x0049
            r1 = r19
            goto L_0x004a
        L_0x0049:
            r1 = r13
        L_0x004a:
            java.lang.Object r1 = r0.invoke(r1, r14)
            r5.put(r10, r1)
            r1 = r17
            goto L_0x0020
        L_0x0054:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt__GroupingKt.fold(kotlin.collections.Grouping, java.lang.Object, kotlin.jvm.functions.Function2):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(Grouping<T, ? extends K> foldTo, M destination, R r, Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldTo, "$this$foldTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<T> sourceIterator = foldTo.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key$iv = foldTo.keyOf(next);
            Object accumulator$iv = destination.get(key$iv);
            boolean first = accumulator$iv == null && !destination.containsKey(key$iv);
            destination.put(key$iv, operation.invoke(first ? r : accumulator$iv, next));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <S, T extends S, K> Map<K, S> reduce(Grouping<T, ? extends K> reduce, Function3<? super K, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> sourceIterator = reduce.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key$iv$iv = (Object) reduce.keyOf(next);
            Object accumulator$iv$iv = (Object) linkedHashMap.get(key$iv$iv);
            boolean first = accumulator$iv$iv == null && !linkedHashMap.containsKey(key$iv$iv);
            Object e = next;
            if (!first) {
                e = operation.invoke(key$iv$iv, accumulator$iv$iv, e);
            }
            linkedHashMap.put(key$iv$iv, e);
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <S, T extends S, K, M extends Map<? super K, S>> M reduceTo(Grouping<T, ? extends K> reduceTo, M destination, Function3<? super K, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(reduceTo, "$this$reduceTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<T> sourceIterator = reduceTo.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key$iv = (Object) reduceTo.keyOf(next);
            Object accumulator$iv = (Object) destination.get(key$iv);
            boolean first = accumulator$iv == null && !destination.containsKey(key$iv);
            Object e = next;
            if (!first) {
                e = operation.invoke(key$iv, accumulator$iv, e);
            }
            destination.put(key$iv, e);
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, K, M extends Map<? super K, Integer>> M eachCountTo(Grouping<T, ? extends K> eachCountTo, M destination) {
        boolean first$iv;
        Object obj;
        Intrinsics.checkNotNullParameter(eachCountTo, "$this$eachCountTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Iterator<T> sourceIterator = eachCountTo.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object key$iv$iv = eachCountTo.keyOf(sourceIterator.next());
            Object accumulator$iv$iv = destination.get(key$iv$iv);
            if (accumulator$iv$iv != null || destination.containsKey(key$iv$iv)) {
                first$iv = false;
            } else {
                first$iv = true;
            }
            if (first$iv) {
                obj = 0;
            } else {
                obj = accumulator$iv$iv;
            }
            int acc = ((Number) obj).intValue();
            destination.put(key$iv$iv, Integer.valueOf(acc + 1));
        }
        return destination;
    }
}
