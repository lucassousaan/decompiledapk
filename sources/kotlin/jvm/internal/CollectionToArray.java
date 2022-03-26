package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;

/* compiled from: CollectionToArray.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];
    private static final int MAX_SIZE = 2147483645;

    public static final Object[] toArray(Collection<?> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        int size$iv = collection.size();
        if (size$iv == 0) {
            return EMPTY;
        }
        Iterator iter$iv = collection.iterator();
        if (!iter$iv.hasNext()) {
            return EMPTY;
        }
        Object[] result$iv = new Object[size$iv];
        int i$iv = 0;
        while (true) {
            int i$iv2 = i$iv + 1;
            result$iv[i$iv] = iter$iv.next();
            if (i$iv2 >= result$iv.length) {
                if (!iter$iv.hasNext()) {
                    return result$iv;
                }
                int newSize$iv = ((i$iv2 * 3) + 1) >>> 1;
                if (newSize$iv <= i$iv2) {
                    if (i$iv2 < MAX_SIZE) {
                        newSize$iv = MAX_SIZE;
                    } else {
                        throw new OutOfMemoryError();
                    }
                }
                Object[] copyOf = Arrays.copyOf(result$iv, newSize$iv);
                Intrinsics.checkNotNullExpressionValue(copyOf, "Arrays.copyOf(result, newSize)");
                result$iv = copyOf;
            } else if (!iter$iv.hasNext()) {
                Object[] copyOf2 = Arrays.copyOf(result$iv, i$iv2);
                Intrinsics.checkNotNullExpressionValue(copyOf2, "Arrays.copyOf(result, size)");
                return copyOf2;
            }
            i$iv = i$iv2;
        }
    }

    public static final Object[] toArray(Collection<?> collection, Object[] a) {
        Object[] objArr;
        Intrinsics.checkNotNullParameter(collection, "collection");
        if (a != null) {
            int size$iv = collection.size();
            if (size$iv != 0) {
                Iterator iter$iv = collection.iterator();
                if (iter$iv.hasNext()) {
                    if (size$iv <= a.length) {
                        objArr = a;
                    } else {
                        Object newInstance = Array.newInstance(a.getClass().getComponentType(), size$iv);
                        if (newInstance != null) {
                            objArr = (Object[]) newInstance;
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                        }
                    }
                    Object[] result$iv = objArr;
                    int i$iv = 0;
                    while (true) {
                        int i$iv2 = i$iv + 1;
                        result$iv[i$iv] = iter$iv.next();
                        if (i$iv2 >= result$iv.length) {
                            if (!iter$iv.hasNext()) {
                                return result$iv;
                            }
                            int newSize$iv = ((i$iv2 * 3) + 1) >>> 1;
                            if (newSize$iv <= i$iv2) {
                                if (i$iv2 < MAX_SIZE) {
                                    newSize$iv = MAX_SIZE;
                                } else {
                                    throw new OutOfMemoryError();
                                }
                            }
                            Object[] copyOf = Arrays.copyOf(result$iv, newSize$iv);
                            Intrinsics.checkNotNullExpressionValue(copyOf, "Arrays.copyOf(result, newSize)");
                            result$iv = copyOf;
                        } else if (!iter$iv.hasNext()) {
                            if (result$iv == a) {
                                a[i$iv2] = null;
                            } else {
                                Object[] result$iv2 = Arrays.copyOf(result$iv, i$iv2);
                                Intrinsics.checkNotNullExpressionValue(result$iv2, "Arrays.copyOf(result, size)");
                                return result$iv2;
                            }
                        }
                        i$iv = i$iv2;
                    }
                } else if (a.length > 0) {
                    a[0] = null;
                }
            } else if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }
        throw new NullPointerException();
    }

    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.lang.Object[] toArrayImpl(java.util.Collection<?> r8, kotlin.jvm.functions.Function0<java.lang.Object[]> r9, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Object[]> r10, kotlin.jvm.functions.Function2<? super java.lang.Object[], ? super java.lang.Integer, java.lang.Object[]> r11) {
        /*
            r0 = 0
            int r1 = r8.size()
            if (r1 != 0) goto L_0x000e
            java.lang.Object r2 = r9.invoke()
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            return r2
        L_0x000e:
            java.util.Iterator r2 = r8.iterator()
            boolean r3 = r2.hasNext()
            if (r3 != 0) goto L_0x001f
            java.lang.Object r3 = r9.invoke()
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            return r3
        L_0x001f:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
            java.lang.Object r3 = r10.invoke(r3)
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            r4 = 0
        L_0x002a:
            int r5 = r4 + 1
            java.lang.Object r6 = r2.next()
            r3[r4] = r6
            int r4 = r3.length
            if (r5 < r4) goto L_0x0061
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x003d
            return r3
        L_0x003d:
            int r4 = r5 * 3
            int r4 = r4 + 1
            int r4 = r4 >>> 1
            if (r4 > r5) goto L_0x0056
            r6 = 2147483645(0x7ffffffd, float:NaN)
            if (r5 >= r6) goto L_0x004e
            r4 = 2147483645(0x7ffffffd, float:NaN)
            goto L_0x0056
        L_0x004e:
            java.lang.OutOfMemoryError r6 = new java.lang.OutOfMemoryError
            r6.<init>()
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        L_0x0056:
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r3, r4)
            java.lang.String r7 = "Arrays.copyOf(result, newSize)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r3 = r6
            goto L_0x0072
        L_0x0061:
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x0072
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r11.invoke(r3, r4)
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            return r4
        L_0x0072:
            r4 = r5
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.CollectionToArray.toArrayImpl(java.util.Collection, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2):java.lang.Object[]");
    }
}
