package androidx.core.location;

import android.location.LocationListener;
import android.os.Bundle;

/* loaded from: classes.dex */
public interface LocationListenerCompat extends LocationListener {
    @Override // android.location.LocationListener
    default void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override // android.location.LocationListener
    default void onProviderEnabled(String provider) {
    }

    @Override // android.location.LocationListener
    default void onProviderDisabled(String provider) {
    }
}
