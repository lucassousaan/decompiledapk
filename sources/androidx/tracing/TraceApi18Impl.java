package androidx.tracing;

import android.os.Trace;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class TraceApi18Impl {
    private TraceApi18Impl() {
    }

    public static void beginSection(String label) {
        Trace.beginSection(label);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
