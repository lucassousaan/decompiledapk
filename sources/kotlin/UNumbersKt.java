package kotlin;

/* compiled from: UNumbers.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b)\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0004\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0007\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\n\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\r\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0004\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0007\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\n\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\r\u001a\u001f\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u0018\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001f\u0010\u0018\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001f\u0010\u0018\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u001f\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b#\u0010\u001b\u001a\u001f\u0010\"\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b$\u0010\u001d\u001a\u001f\u0010\"\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u001f\u001a\u001f\u0010\"\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010!\u001a\u0017\u0010'\u001a\u00020\u0002*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a\u0017\u0010'\u001a\u00020\u0005*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0007\u001a\u0017\u0010'\u001a\u00020\b*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u0017\u0010'\u001a\u00020\u000b*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u0017\u0010/\u001a\u00020\u0002*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b0\u0010)\u001a\u0017\u0010/\u001a\u00020\u0005*\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b1\u0010\u0007\u001a\u0017\u0010/\u001a\u00020\b*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\b2\u0010,\u001a\u0017\u0010/\u001a\u00020\u000b*\u00020\u000bH\u0087\bø\u0001\u0000¢\u0006\u0004\b3\u0010.\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"countLeadingZeroBits", "", "Lkotlin/UByte;", "countLeadingZeroBits-7apg3OU", "(B)I", "Lkotlin/UInt;", "countLeadingZeroBits-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "countLeadingZeroBits-VKZWuLQ", "(J)I", "Lkotlin/UShort;", "countLeadingZeroBits-xj2QHRw", "(S)I", "countOneBits", "countOneBits-7apg3OU", "countOneBits-WZ4Q5Ns", "countOneBits-VKZWuLQ", "countOneBits-xj2QHRw", "countTrailingZeroBits", "countTrailingZeroBits-7apg3OU", "countTrailingZeroBits-WZ4Q5Ns", "countTrailingZeroBits-VKZWuLQ", "countTrailingZeroBits-xj2QHRw", "rotateLeft", "bitCount", "rotateLeft-LxnNnR4", "(BI)B", "rotateLeft-V7xB4Y4", "(II)I", "rotateLeft-JSWoG40", "(JI)J", "rotateLeft-olVBNx4", "(SI)S", "rotateRight", "rotateRight-LxnNnR4", "rotateRight-V7xB4Y4", "rotateRight-JSWoG40", "rotateRight-olVBNx4", "takeHighestOneBit", "takeHighestOneBit-7apg3OU", "(B)B", "takeHighestOneBit-WZ4Q5Ns", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit", "takeLowestOneBit-7apg3OU", "takeLowestOneBit-WZ4Q5Ns", "takeLowestOneBit-VKZWuLQ", "takeLowestOneBit-xj2QHRw", "kotlin-stdlib"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class UNumbersKt {
    /* renamed from: countOneBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m273countOneBitsWZ4Q5Ns(int $this$countOneBits) {
        return Integer.bitCount($this$countOneBits);
    }

    /* renamed from: countLeadingZeroBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m269countLeadingZeroBitsWZ4Q5Ns(int $this$countLeadingZeroBits) {
        return Integer.numberOfLeadingZeros($this$countLeadingZeroBits);
    }

    /* renamed from: countTrailingZeroBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m277countTrailingZeroBitsWZ4Q5Ns(int $this$countTrailingZeroBits) {
        return Integer.numberOfTrailingZeros($this$countTrailingZeroBits);
    }

    /* renamed from: takeHighestOneBit-WZ4Q5Ns  reason: not valid java name */
    private static final int m289takeHighestOneBitWZ4Q5Ns(int $this$takeHighestOneBit) {
        return UInt.m118constructorimpl(Integer.highestOneBit($this$takeHighestOneBit));
    }

    /* renamed from: takeLowestOneBit-WZ4Q5Ns  reason: not valid java name */
    private static final int m293takeLowestOneBitWZ4Q5Ns(int $this$takeLowestOneBit) {
        return UInt.m118constructorimpl(Integer.lowestOneBit($this$takeLowestOneBit));
    }

    /* renamed from: rotateLeft-V7xB4Y4  reason: not valid java name */
    private static final int m281rotateLeftV7xB4Y4(int $this$rotateLeft, int bitCount) {
        return UInt.m118constructorimpl(Integer.rotateLeft($this$rotateLeft, bitCount));
    }

    /* renamed from: rotateRight-V7xB4Y4  reason: not valid java name */
    private static final int m285rotateRightV7xB4Y4(int $this$rotateRight, int bitCount) {
        return UInt.m118constructorimpl(Integer.rotateRight($this$rotateRight, bitCount));
    }

    /* renamed from: countOneBits-VKZWuLQ  reason: not valid java name */
    private static final int m272countOneBitsVKZWuLQ(long $this$countOneBits) {
        return Long.bitCount($this$countOneBits);
    }

    /* renamed from: countLeadingZeroBits-VKZWuLQ  reason: not valid java name */
    private static final int m268countLeadingZeroBitsVKZWuLQ(long $this$countLeadingZeroBits) {
        return Long.numberOfLeadingZeros($this$countLeadingZeroBits);
    }

    /* renamed from: countTrailingZeroBits-VKZWuLQ  reason: not valid java name */
    private static final int m276countTrailingZeroBitsVKZWuLQ(long $this$countTrailingZeroBits) {
        return Long.numberOfTrailingZeros($this$countTrailingZeroBits);
    }

    /* renamed from: takeHighestOneBit-VKZWuLQ  reason: not valid java name */
    private static final long m288takeHighestOneBitVKZWuLQ(long $this$takeHighestOneBit) {
        return ULong.m196constructorimpl(Long.highestOneBit($this$takeHighestOneBit));
    }

    /* renamed from: takeLowestOneBit-VKZWuLQ  reason: not valid java name */
    private static final long m292takeLowestOneBitVKZWuLQ(long $this$takeLowestOneBit) {
        return ULong.m196constructorimpl(Long.lowestOneBit($this$takeLowestOneBit));
    }

    /* renamed from: rotateLeft-JSWoG40  reason: not valid java name */
    private static final long m279rotateLeftJSWoG40(long $this$rotateLeft, int bitCount) {
        return ULong.m196constructorimpl(Long.rotateLeft($this$rotateLeft, bitCount));
    }

    /* renamed from: rotateRight-JSWoG40  reason: not valid java name */
    private static final long m283rotateRightJSWoG40(long $this$rotateRight, int bitCount) {
        return ULong.m196constructorimpl(Long.rotateRight($this$rotateRight, bitCount));
    }

    /* renamed from: countOneBits-7apg3OU  reason: not valid java name */
    private static final int m271countOneBits7apg3OU(byte $this$countOneBits) {
        return Integer.bitCount(UInt.m118constructorimpl($this$countOneBits & UByte.MAX_VALUE));
    }

    /* renamed from: countLeadingZeroBits-7apg3OU  reason: not valid java name */
    private static final int m267countLeadingZeroBits7apg3OU(byte $this$countLeadingZeroBits) {
        return Integer.numberOfLeadingZeros($this$countLeadingZeroBits & UByte.MAX_VALUE) - 24;
    }

    /* renamed from: countTrailingZeroBits-7apg3OU  reason: not valid java name */
    private static final int m275countTrailingZeroBits7apg3OU(byte $this$countTrailingZeroBits) {
        return Integer.numberOfTrailingZeros($this$countTrailingZeroBits | UByte.MIN_VALUE);
    }

    /* renamed from: takeHighestOneBit-7apg3OU  reason: not valid java name */
    private static final byte m287takeHighestOneBit7apg3OU(byte $this$takeHighestOneBit) {
        return UByte.m42constructorimpl((byte) Integer.highestOneBit($this$takeHighestOneBit & UByte.MAX_VALUE));
    }

    /* renamed from: takeLowestOneBit-7apg3OU  reason: not valid java name */
    private static final byte m291takeLowestOneBit7apg3OU(byte $this$takeLowestOneBit) {
        return UByte.m42constructorimpl((byte) Integer.lowestOneBit($this$takeLowestOneBit & UByte.MAX_VALUE));
    }

    /* renamed from: rotateLeft-LxnNnR4  reason: not valid java name */
    private static final byte m280rotateLeftLxnNnR4(byte $this$rotateLeft, int bitCount) {
        return UByte.m42constructorimpl(NumbersKt.rotateLeft($this$rotateLeft, bitCount));
    }

    /* renamed from: rotateRight-LxnNnR4  reason: not valid java name */
    private static final byte m284rotateRightLxnNnR4(byte $this$rotateRight, int bitCount) {
        return UByte.m42constructorimpl(NumbersKt.rotateRight($this$rotateRight, bitCount));
    }

    /* renamed from: countOneBits-xj2QHRw  reason: not valid java name */
    private static final int m274countOneBitsxj2QHRw(short $this$countOneBits) {
        return Integer.bitCount(UInt.m118constructorimpl(65535 & $this$countOneBits));
    }

    /* renamed from: countLeadingZeroBits-xj2QHRw  reason: not valid java name */
    private static final int m270countLeadingZeroBitsxj2QHRw(short $this$countLeadingZeroBits) {
        return Integer.numberOfLeadingZeros(65535 & $this$countLeadingZeroBits) - 16;
    }

    /* renamed from: countTrailingZeroBits-xj2QHRw  reason: not valid java name */
    private static final int m278countTrailingZeroBitsxj2QHRw(short $this$countTrailingZeroBits) {
        return Integer.numberOfTrailingZeros(65536 | $this$countTrailingZeroBits);
    }

    /* renamed from: takeHighestOneBit-xj2QHRw  reason: not valid java name */
    private static final short m290takeHighestOneBitxj2QHRw(short $this$takeHighestOneBit) {
        return UShort.m302constructorimpl((short) Integer.highestOneBit(65535 & $this$takeHighestOneBit));
    }

    /* renamed from: takeLowestOneBit-xj2QHRw  reason: not valid java name */
    private static final short m294takeLowestOneBitxj2QHRw(short $this$takeLowestOneBit) {
        return UShort.m302constructorimpl((short) Integer.lowestOneBit(65535 & $this$takeLowestOneBit));
    }

    /* renamed from: rotateLeft-olVBNx4  reason: not valid java name */
    private static final short m282rotateLeftolVBNx4(short $this$rotateLeft, int bitCount) {
        return UShort.m302constructorimpl(NumbersKt.rotateLeft($this$rotateLeft, bitCount));
    }

    /* renamed from: rotateRight-olVBNx4  reason: not valid java name */
    private static final short m286rotateRightolVBNx4(short $this$rotateRight, int bitCount) {
        return UShort.m302constructorimpl(NumbersKt.rotateRight($this$rotateRight, bitCount));
    }
}
