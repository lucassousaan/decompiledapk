package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* compiled from: _Maps.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001aJ\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a$\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a9\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001a6\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a'\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001aJ\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a[\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a]\u0010\u0014\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\\\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\u0086\bø\u0001\u0000\u001aa\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0017\u001au\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aw\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001c\u001aJ\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010 \u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0\u0006H\u0087\bø\u0001\u0000\u001aV\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0086\bø\u0001\u0000\u001a\\\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0086\bø\u0001\u0000\u001au\u0010#\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ao\u0010$\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001a*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u0002H\u00192\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ah\u0010%\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000\u001ah\u0010(\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000\u001a_\u0010)\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aJ\u0010)\u001a\u00020+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\u0087\bø\u0001\u0000\u001aJ\u0010)\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000\u001aa\u0010-\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aQ\u0010-\u001a\u0004\u0018\u00010+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010.\u001aQ\u0010-\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010/\u001aq\u00100\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00104\u001as\u00105\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00104\u001ai\u00106\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\u0087\b\u001ai\u00107\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\u0087\b\u001ah\u00108\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000\u001ah\u00109\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000\u001a_\u0010:\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aJ\u0010:\u001a\u00020+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\u0087\bø\u0001\u0000\u001aJ\u0010:\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000\u001aa\u0010;\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001aQ\u0010;\u001a\u0004\u0018\u00010+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020+0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010.\u001aQ\u0010;\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010/\u001aq\u0010<\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00104\u001as\u0010=\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u00101\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001002j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`32\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u00104\u001ah\u0010>\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\u0007\u001ai\u0010?\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00101\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000702j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`3H\u0087\b\u001a$\u0010@\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aJ\u0010@\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001aY\u0010A\u001a\u0002HB\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010B*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HB2\u001e\u0010 \u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010C\u001an\u0010D\u001a\u0002HB\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010B*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HB23\u0010 \u001a/\u0012\u0013\u0012\u00110\u000e¢\u0006\f\bF\u0012\b\bG\u0012\u0004\b\b(H\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001f0EH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010I\u001a6\u0010J\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030K0\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006L"}, d2 = {"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "firstNotNullOf", "R", "", "transform", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstNotNullOfOrNull", "flatMap", "", "flatMapSequence", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "flatMapSequenceTo", "forEach", "", "action", "map", "mapNotNull", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxByOrNull", "maxOf", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "", "", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "maxOfWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWith", "maxWithOrNull", "minBy", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWith", "minWithOrNull", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "onEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 5, 1}, xi = 1, xs = "kotlin/collections/MapsKt")
/* loaded from: classes.dex */
class MapsKt___MapsKt extends MapsKt__MapsKt {
    private static final <K, V, R> R firstNotNullOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        R r;
        Iterator<Map.Entry<? extends K, ? extends V>> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                r = null;
                break;
            }
            r = (R) function1.invoke(it.next());
            if (r != null) {
                break;
            }
        }
        if (r != null) {
            return r;
        }
        throw new NoSuchElementException("No element of the map was transformed to a non-null value.");
    }

    private static final <K, V, R> R firstNotNullOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        for (Map.Entry element : map.entrySet()) {
            R r = (R) function1.invoke(element);
            if (r != null) {
                return r;
            }
        }
        return null;
    }

    public static final <K, V> List<Pair<K, V>> toList(Map<? extends K, ? extends V> toList) {
        Intrinsics.checkNotNullParameter(toList, "$this$toList");
        if (toList.size() == 0) {
            return CollectionsKt.emptyList();
        }
        Iterator iterator = toList.entrySet().iterator();
        if (!iterator.hasNext()) {
            return CollectionsKt.emptyList();
        }
        Map.Entry<? extends K, ? extends V> first = iterator.next();
        if (!iterator.hasNext()) {
            return CollectionsKt.listOf(new Pair(first.getKey(), first.getValue()));
        }
        ArrayList result = new ArrayList(toList.size());
        result.add(new Pair(first.getKey(), first.getValue()));
        do {
            Map.Entry<? extends K, ? extends V> next = iterator.next();
            result.add(new Pair(next.getKey(), next.getValue()));
        } while (iterator.hasNext());
        return result;
    }

    public static final <K, V, R> List<R> flatMap(Map<? extends K, ? extends V> flatMap, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Collection destination$iv = new ArrayList();
        for (Map.Entry element$iv : flatMap.entrySet()) {
            Iterable list$iv = (Iterable) transform.invoke(element$iv);
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (List) destination$iv;
    }

    public static final <K, V, R> List<R> flatMapSequence(Map<? extends K, ? extends V> flatMap, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Collection destination$iv = new ArrayList();
        for (Map.Entry element$iv : flatMap.entrySet()) {
            Sequence list$iv = (Sequence) transform.invoke(element$iv);
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (List) destination$iv;
    }

    public static final <K, V, R, C extends Collection<? super R>> C flatMapTo(Map<? extends K, ? extends V> flatMapTo, C destination, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (Map.Entry element : flatMapTo.entrySet()) {
            Iterable list = (Iterable) transform.invoke(element);
            CollectionsKt.addAll(destination, list);
        }
        return destination;
    }

    public static final <K, V, R, C extends Collection<? super R>> C flatMapSequenceTo(Map<? extends K, ? extends V> flatMapTo, C destination, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (Map.Entry element : flatMapTo.entrySet()) {
            Sequence list = (Sequence) transform.invoke(element);
            CollectionsKt.addAll(destination, list);
        }
        return destination;
    }

    public static final <K, V, R> List<R> map(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Collection destination$iv = new ArrayList(map.size());
        for (Map.Entry item$iv : map.entrySet()) {
            destination$iv.add(transform.invoke(item$iv));
        }
        return (List) destination$iv;
    }

    public static final <K, V, R> List<R> mapNotNull(Map<? extends K, ? extends V> mapNotNull, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapNotNull, "$this$mapNotNull");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Collection destination$iv = new ArrayList();
        for (Map.Entry element$iv$iv : mapNotNull.entrySet()) {
            Object it$iv = transform.invoke(element$iv$iv);
            if (it$iv != null) {
                destination$iv.add(it$iv);
            }
        }
        return (List) destination$iv;
    }

    public static final <K, V, R, C extends Collection<? super R>> C mapNotNullTo(Map<? extends K, ? extends V> mapNotNullTo, C destination, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapNotNullTo, "$this$mapNotNullTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (Map.Entry element$iv : mapNotNullTo.entrySet()) {
            Object it = transform.invoke(element$iv);
            if (it != null) {
                destination.add(it);
            }
        }
        return destination;
    }

    public static final <K, V, R, C extends Collection<? super R>> C mapTo(Map<? extends K, ? extends V> mapTo, C destination, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (Map.Entry item : mapTo.entrySet()) {
            destination.add(transform.invoke(item));
        }
        return destination;
    }

    public static final <K, V> boolean all(Map<? extends K, ? extends V> all, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (all.isEmpty()) {
            return true;
        }
        for (Map.Entry element : all.entrySet()) {
            if (!predicate.invoke(element).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> any) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        return !any.isEmpty();
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> any, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (any.isEmpty()) {
            return false;
        }
        for (Map.Entry element : any.entrySet()) {
            if (predicate.invoke(element).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private static final <K, V> int count(Map<? extends K, ? extends V> map) {
        return map.size();
    }

    public static final <K, V> int count(Map<? extends K, ? extends V> count, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (count.isEmpty()) {
            return 0;
        }
        int count2 = 0;
        for (Map.Entry element : count.entrySet()) {
            if (predicate.invoke(element).booleanValue()) {
                count2++;
            }
        }
        return count2;
    }

    public static final <K, V> void forEach(Map<? extends K, ? extends V> forEach, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry element : forEach.entrySet()) {
            action.invoke(element);
        }
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxBy(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Object obj;
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            obj = null;
        } else {
            obj = (Object) it.next();
            if (it.hasNext()) {
                Comparable comparable = (Comparable) function1.invoke(obj);
                do {
                    Object obj2 = (Object) it.next();
                    Comparable comparable2 = (Comparable) function1.invoke(obj2);
                    if (comparable.compareTo(comparable2) < 0) {
                        obj = (Object) obj2;
                        comparable = comparable2;
                    }
                } while (it.hasNext());
            }
        }
        return (Map.Entry) obj;
    }

    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxByOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Object maxElem$iv;
        Iterable $this$maxByOrNull$iv = map.entrySet();
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (!iterator$iv.hasNext()) {
            maxElem$iv = (Object) null;
        } else {
            maxElem$iv = (Object) iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Comparable maxValue$iv = (Comparable) function1.invoke(maxElem$iv);
                do {
                    Object e$iv = (Object) iterator$iv.next();
                    Comparable v$iv = (Comparable) function1.invoke(e$iv);
                    if (maxValue$iv.compareTo(v$iv) < 0) {
                        Object maxElem$iv2 = e$iv;
                        maxElem$iv = (Object) maxElem$iv2;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        }
        return (Map.Entry) maxElem$iv;
    }

    private static final <K, V> double maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (it.hasNext()) {
            double doubleValue = function1.invoke((Object) it.next()).doubleValue();
            while (it.hasNext()) {
                doubleValue = Math.max(doubleValue, function1.invoke((Object) it.next()).doubleValue());
            }
            return doubleValue;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: maxOf  reason: collision with other method in class */
    private static final <K, V> float m463maxOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (it.hasNext()) {
            float floatValue = function1.invoke((Object) it.next()).floatValue();
            while (it.hasNext()) {
                floatValue = Math.max(floatValue, function1.invoke((Object) it.next()).floatValue());
            }
            return floatValue;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Comparable, java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: maxOf  reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final <K, V, R extends java.lang.Comparable<? super R>> R m464maxOf(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            r0 = 0
            java.util.Set r1 = r5.entrySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0034
            java.lang.Object r2 = r1.next()
            java.lang.Object r2 = r6.invoke(r2)
            java.lang.Comparable r2 = (java.lang.Comparable) r2
        L_0x001b:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0033
            java.lang.Object r3 = r1.next()
            java.lang.Object r3 = r6.invoke(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r2.compareTo(r3)
            if (r4 >= 0) goto L_0x001b
            r2 = r3
            goto L_0x001b
        L_0x0033:
            return r2
        L_0x0034:
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            r1.<init>()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.m464maxOf(java.util.Map, kotlin.jvm.functions.Function1):java.lang.Comparable");
    }

    /* renamed from: maxOfOrNull  reason: collision with other method in class */
    private static final <K, V> Double m465maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        double doubleValue = function1.invoke((Object) it.next()).doubleValue();
        while (it.hasNext()) {
            doubleValue = Math.max(doubleValue, function1.invoke((Object) it.next()).doubleValue());
        }
        return Double.valueOf(doubleValue);
    }

    /* renamed from: maxOfOrNull  reason: collision with other method in class */
    private static final <K, V> Float m466maxOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        float floatValue = function1.invoke((Object) it.next()).floatValue();
        while (it.hasNext()) {
            floatValue = Math.max(floatValue, function1.invoke((Object) it.next()).floatValue());
        }
        return Float.valueOf(floatValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Comparable, java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final <K, V, R extends java.lang.Comparable<? super R>> R maxOfOrNull(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            r0 = 0
            java.util.Set r1 = r5.entrySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            boolean r2 = r1.hasNext()
            if (r2 != 0) goto L_0x0013
            r1 = 0
            goto L_0x0036
        L_0x0013:
            java.lang.Object r2 = r1.next()
            java.lang.Object r2 = r6.invoke(r2)
            java.lang.Comparable r2 = (java.lang.Comparable) r2
        L_0x001d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0035
            java.lang.Object r3 = r1.next()
            java.lang.Object r3 = r6.invoke(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r2.compareTo(r3)
            if (r4 >= 0) goto L_0x001d
            r2 = r3
            goto L_0x001d
        L_0x0035:
            r1 = r2
        L_0x0036:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.maxOfOrNull(java.util.Map, kotlin.jvm.functions.Function1):java.lang.Comparable");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <K, V, R> R maxOfWith(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            Object obj = (R) function1.invoke((Object) it.next());
            while (it.hasNext()) {
                Object obj2 = (Object) function1.invoke((Object) it.next());
                if (comparator.compare(obj, obj2) < 0) {
                    obj = (R) obj2;
                }
            }
            return (R) obj;
        }
        throw new NoSuchElementException();
    }

    private static final <K, V, R> R maxOfWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object obj = (Object) function1.invoke((Object) it.next());
        while (it.hasNext()) {
            Object obj2 = (Object) function1.invoke((Object) it.next());
            if (comparator.compare(obj, obj2) < 0) {
                obj = (Object) obj2;
            }
        }
        return (R) obj;
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    private static final <K, V> Map.Entry<K, V> maxWith(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        return (Map.Entry) CollectionsKt.maxWithOrNull(map.entrySet(), comparator);
    }

    private static final <K, V> Map.Entry<K, V> maxWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        return (Map.Entry) CollectionsKt.maxWithOrNull(map.entrySet(), comparator);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    public static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minBy(Map<? extends K, ? extends V> minBy, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> selector) {
        Object obj;
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<T> it = minBy.entrySet().iterator();
        if (!it.hasNext()) {
            obj = null;
        } else {
            obj = (Object) it.next();
            if (it.hasNext()) {
                Comparable comparable = (Comparable) selector.invoke(obj);
                do {
                    Object obj2 = (Object) it.next();
                    Comparable comparable2 = (Comparable) selector.invoke(obj2);
                    if (comparable.compareTo(comparable2) > 0) {
                        obj = (Object) obj2;
                        comparable = comparable2;
                    }
                } while (it.hasNext());
            }
        }
        return (Map.Entry) obj;
    }

    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minByOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Object minElem$iv;
        Iterable $this$minByOrNull$iv = map.entrySet();
        Iterator iterator$iv = $this$minByOrNull$iv.iterator();
        if (!iterator$iv.hasNext()) {
            minElem$iv = (Object) null;
        } else {
            minElem$iv = (Object) iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Comparable minValue$iv = (Comparable) function1.invoke(minElem$iv);
                do {
                    Object e$iv = (Object) iterator$iv.next();
                    Comparable v$iv = (Comparable) function1.invoke(e$iv);
                    if (minValue$iv.compareTo(v$iv) > 0) {
                        Object minElem$iv2 = e$iv;
                        minElem$iv = (Object) minElem$iv2;
                        minValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        }
        return (Map.Entry) minElem$iv;
    }

    private static final <K, V> double minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (it.hasNext()) {
            double doubleValue = function1.invoke((Object) it.next()).doubleValue();
            while (it.hasNext()) {
                doubleValue = Math.min(doubleValue, function1.invoke((Object) it.next()).doubleValue());
            }
            return doubleValue;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: minOf  reason: collision with other method in class */
    private static final <K, V> float m467minOf(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (it.hasNext()) {
            float floatValue = function1.invoke((Object) it.next()).floatValue();
            while (it.hasNext()) {
                floatValue = Math.min(floatValue, function1.invoke((Object) it.next()).floatValue());
            }
            return floatValue;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Comparable, java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: minOf  reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final <K, V, R extends java.lang.Comparable<? super R>> R m468minOf(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            r0 = 0
            java.util.Set r1 = r5.entrySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0034
            java.lang.Object r2 = r1.next()
            java.lang.Object r2 = r6.invoke(r2)
            java.lang.Comparable r2 = (java.lang.Comparable) r2
        L_0x001b:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0033
            java.lang.Object r3 = r1.next()
            java.lang.Object r3 = r6.invoke(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r2.compareTo(r3)
            if (r4 <= 0) goto L_0x001b
            r2 = r3
            goto L_0x001b
        L_0x0033:
            return r2
        L_0x0034:
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            r1.<init>()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.m468minOf(java.util.Map, kotlin.jvm.functions.Function1):java.lang.Comparable");
    }

    /* renamed from: minOfOrNull  reason: collision with other method in class */
    private static final <K, V> Double m469minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Double> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        double doubleValue = function1.invoke((Object) it.next()).doubleValue();
        while (it.hasNext()) {
            doubleValue = Math.min(doubleValue, function1.invoke((Object) it.next()).doubleValue());
        }
        return Double.valueOf(doubleValue);
    }

    /* renamed from: minOfOrNull  reason: collision with other method in class */
    private static final <K, V> Float m470minOfOrNull(Map<? extends K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, Float> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        float floatValue = function1.invoke((Object) it.next()).floatValue();
        while (it.hasNext()) {
            floatValue = Math.min(floatValue, function1.invoke((Object) it.next()).floatValue());
        }
        return Float.valueOf(floatValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Comparable, java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final <K, V, R extends java.lang.Comparable<? super R>> R minOfOrNull(java.util.Map<? extends K, ? extends V> r5, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        /*
            r0 = 0
            java.util.Set r1 = r5.entrySet()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            boolean r2 = r1.hasNext()
            if (r2 != 0) goto L_0x0013
            r1 = 0
            goto L_0x0036
        L_0x0013:
            java.lang.Object r2 = r1.next()
            java.lang.Object r2 = r6.invoke(r2)
            java.lang.Comparable r2 = (java.lang.Comparable) r2
        L_0x001d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0035
            java.lang.Object r3 = r1.next()
            java.lang.Object r3 = r6.invoke(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            int r4 = r2.compareTo(r3)
            if (r4 <= 0) goto L_0x001d
            r2 = r3
            goto L_0x001d
        L_0x0035:
            r1 = r2
        L_0x0036:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.minOfOrNull(java.util.Map, kotlin.jvm.functions.Function1):java.lang.Comparable");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <K, V, R> R minOfWith(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            Object obj = (R) function1.invoke((Object) it.next());
            while (it.hasNext()) {
                Object obj2 = (Object) function1.invoke((Object) it.next());
                if (comparator.compare(obj, obj2) > 0) {
                    obj = (R) obj2;
                }
            }
            return (R) obj;
        }
        throw new NoSuchElementException();
    }

    private static final <K, V, R> R minOfWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super R> comparator, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterator<T> it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object obj = (Object) function1.invoke((Object) it.next());
        while (it.hasNext()) {
            Object obj2 = (Object) function1.invoke((Object) it.next());
            if (comparator.compare(obj, obj2) > 0) {
                obj = (Object) obj2;
            }
        }
        return (R) obj;
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", warningSince = "1.4")
    public static final <K, V> Map.Entry<K, V> minWith(Map<? extends K, ? extends V> minWith, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return (Map.Entry) CollectionsKt.minWithOrNull(minWith.entrySet(), comparator);
    }

    private static final <K, V> Map.Entry<K, V> minWithOrNull(Map<? extends K, ? extends V> map, Comparator<? super Map.Entry<? extends K, ? extends V>> comparator) {
        return (Map.Entry) CollectionsKt.minWithOrNull(map.entrySet(), comparator);
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return none.isEmpty();
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> none, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (none.isEmpty()) {
            return true;
        }
        for (Map.Entry element : none.entrySet()) {
            if (predicate.invoke(element).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEach(M onEach, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry element : onEach.entrySet()) {
            action.invoke(element);
        }
        return onEach;
    }

    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEachIndexed(M onEachIndexed, Function2<? super Integer, ? super Map.Entry<? extends K, ? extends V>, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        Iterable<Object> $this$forEachIndexed$iv = onEachIndexed.entrySet();
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            action.invoke(Integer.valueOf(index$iv), item$iv);
            index$iv = index$iv2;
        }
        return onEachIndexed;
    }

    private static final <K, V> Iterable<Map.Entry<K, V>> asIterable(Map<? extends K, ? extends V> map) {
        return map.entrySet();
    }

    public static final <K, V> Sequence<Map.Entry<K, V>> asSequence(Map<? extends K, ? extends V> asSequence) {
        Intrinsics.checkNotNullParameter(asSequence, "$this$asSequence");
        return CollectionsKt.asSequence(asSequence.entrySet());
    }
}
