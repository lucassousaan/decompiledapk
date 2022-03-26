package kotlin.collections;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: _UCollections.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, k = 5, mv = {1, 5, 1}, xi = 1, xs = "kotlin/collections/UCollectionsKt")
/* loaded from: classes.dex */
class UCollectionsKt___UCollectionsKt {
    public static final byte[] toUByteArray(Collection<UByte> toUByteArray) {
        Intrinsics.checkNotNullParameter(toUByteArray, "$this$toUByteArray");
        byte[] result = UByteArray.m93constructorimpl(toUByteArray.size());
        int index = 0;
        for (UByte uByte : toUByteArray) {
            byte element = uByte.m91unboximpl();
            UByteArray.m104setVurrAj0(result, index, element);
            index++;
        }
        return result;
    }

    public static final int[] toUIntArray(Collection<UInt> toUIntArray) {
        Intrinsics.checkNotNullParameter(toUIntArray, "$this$toUIntArray");
        int[] result = UIntArray.m171constructorimpl(toUIntArray.size());
        int index = 0;
        for (UInt uInt : toUIntArray) {
            int element = uInt.m169unboximpl();
            UIntArray.m182setVXSXFK8(result, index, element);
            index++;
        }
        return result;
    }

    public static final long[] toULongArray(Collection<ULong> toULongArray) {
        Intrinsics.checkNotNullParameter(toULongArray, "$this$toULongArray");
        long[] result = ULongArray.m249constructorimpl(toULongArray.size());
        int index = 0;
        for (ULong uLong : toULongArray) {
            long element = uLong.m247unboximpl();
            ULongArray.m260setk8EXiF4(result, index, element);
            index++;
        }
        return result;
    }

    public static final short[] toUShortArray(Collection<UShort> toUShortArray) {
        Intrinsics.checkNotNullParameter(toUShortArray, "$this$toUShortArray");
        short[] result = UShortArray.m353constructorimpl(toUShortArray.size());
        int index = 0;
        for (UShort uShort : toUShortArray) {
            short element = uShort.m351unboximpl();
            UShortArray.m364set01HTLdE(result, index, element);
            index++;
        }
        return result;
    }

    public static final int sumOfUInt(Iterable<UInt> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int sum2 = 0;
        for (UInt uInt : sum) {
            int element = uInt.m169unboximpl();
            sum2 = UInt.m118constructorimpl(sum2 + element);
        }
        return sum2;
    }

    public static final long sumOfULong(Iterable<ULong> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        long sum2 = 0;
        for (ULong uLong : sum) {
            long element = uLong.m247unboximpl();
            sum2 = ULong.m196constructorimpl(sum2 + element);
        }
        return sum2;
    }

    public static final int sumOfUByte(Iterable<UByte> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int sum2 = 0;
        for (UByte uByte : sum) {
            byte element = uByte.m91unboximpl();
            sum2 = UInt.m118constructorimpl(UInt.m118constructorimpl(element & UByte.MAX_VALUE) + sum2);
        }
        return sum2;
    }

    public static final int sumOfUShort(Iterable<UShort> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int sum2 = 0;
        for (UShort uShort : sum) {
            short element = uShort.m351unboximpl();
            sum2 = UInt.m118constructorimpl(UInt.m118constructorimpl(65535 & element) + sum2);
        }
        return sum2;
    }
}
