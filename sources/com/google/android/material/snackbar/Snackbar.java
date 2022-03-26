package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;

/* loaded from: classes.dex */
public class Snackbar extends BaseTransientBottomBar<Snackbar> {
    private static final int[] SNACKBAR_BUTTON_STYLE_ATTR = {R.attr.snackbarButtonStyle};
    private static final int[] SNACKBAR_CONTENT_STYLE_ATTRS = {R.attr.snackbarButtonStyle, R.attr.snackbarTextViewStyle};
    private final AccessibilityManager accessibilityManager;
    private BaseTransientBottomBar.BaseCallback<Snackbar> callback;
    private boolean hasAction;

    /* loaded from: classes.dex */
    public static class Callback extends BaseTransientBottomBar.BaseCallback<Snackbar> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public void onShown(Snackbar sb) {
        }

        public void onDismissed(Snackbar transientBottomBar, int event) {
        }
    }

    private Snackbar(Context context, ViewGroup parent, View content, ContentViewCallback contentViewCallback) {
        super(context, parent, content, contentViewCallback);
        this.accessibilityManager = (AccessibilityManager) parent.getContext().getSystemService("accessibility");
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void show() {
        super.show();
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void dismiss() {
        super.dismiss();
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public boolean isShown() {
        return super.isShown();
    }

    public static Snackbar make(View view, CharSequence text, int duration) {
        return makeInternal(null, view, text, duration);
    }

    public static Snackbar make(Context context, View view, CharSequence text, int duration) {
        return makeInternal(context, view, text, duration);
    }

    private static Snackbar makeInternal(Context context, View view, CharSequence text, int duration) {
        ViewGroup parent = findSuitableParent(view);
        if (parent != null) {
            if (context == null) {
                context = parent.getContext();
            }
            LayoutInflater inflater = LayoutInflater.from(context);
            SnackbarContentLayout content = (SnackbarContentLayout) inflater.inflate(hasSnackbarContentStyleAttrs(context) ? R.layout.mtrl_layout_snackbar_include : R.layout.design_layout_snackbar_include, parent, false);
            Snackbar snackbar = new Snackbar(context, parent, content, content);
            snackbar.setText(text);
            snackbar.setDuration(duration);
            return snackbar;
        }
        throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
    }

    @Deprecated
    protected static boolean hasSnackbarButtonStyleAttr(Context context) {
        TypedArray a = context.obtainStyledAttributes(SNACKBAR_BUTTON_STYLE_ATTR);
        int snackbarButtonStyleResId = a.getResourceId(0, -1);
        a.recycle();
        return snackbarButtonStyleResId != -1;
    }

    private static boolean hasSnackbarContentStyleAttrs(Context context) {
        TypedArray a = context.obtainStyledAttributes(SNACKBAR_CONTENT_STYLE_ATTRS);
        int snackbarButtonStyleResId = a.getResourceId(0, -1);
        int snackbarTextViewStyleResId = a.getResourceId(1, -1);
        a.recycle();
        if (snackbarButtonStyleResId == -1 || snackbarTextViewStyleResId == -1) {
            return false;
        }
        return true;
    }

    public static Snackbar make(View view, int resId, int duration) {
        return make(view, view.getResources().getText(resId), duration);
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        while (!(view instanceof CoordinatorLayout)) {
            if (view instanceof FrameLayout) {
                if (view.getId() == 16908290) {
                    return (ViewGroup) view;
                }
                fallback = (ViewGroup) view;
            }
            if (view != null) {
                ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
                continue;
            }
            if (view == null) {
                return fallback;
            }
        }
        return (ViewGroup) view;
    }

    public Snackbar setText(CharSequence message) {
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) this.view.getChildAt(0);
        TextView tv = contentLayout.getMessageView();
        tv.setText(message);
        return this;
    }

    public Snackbar setText(int resId) {
        return setText(getContext().getText(resId));
    }

    public Snackbar setAction(int resId, View.OnClickListener listener) {
        return setAction(getContext().getText(resId), listener);
    }

    public Snackbar setAction(CharSequence text, final View.OnClickListener listener) {
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) this.view.getChildAt(0);
        TextView tv = contentLayout.getActionView();
        if (TextUtils.isEmpty(text) || listener == null) {
            tv.setVisibility(8);
            tv.setOnClickListener(null);
            this.hasAction = false;
        } else {
            this.hasAction = true;
            tv.setVisibility(0);
            tv.setText(text);
            tv.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.snackbar.Snackbar.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    listener.onClick(view);
                    Snackbar.this.dispatchDismiss(1);
                }
            });
        }
        return this;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public int getDuration() {
        int userSetDuration = super.getDuration();
        if (userSetDuration == -2) {
            return -2;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            int controlsFlag = this.hasAction ? 4 : 0;
            return this.accessibilityManager.getRecommendedTimeoutMillis(userSetDuration, controlsFlag | 1 | 2);
        } else if (!this.hasAction || !this.accessibilityManager.isTouchExplorationEnabled()) {
            return userSetDuration;
        } else {
            return -2;
        }
    }

    public Snackbar setTextColor(ColorStateList colors) {
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) this.view.getChildAt(0);
        TextView tv = contentLayout.getMessageView();
        tv.setTextColor(colors);
        return this;
    }

    public Snackbar setTextColor(int color) {
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) this.view.getChildAt(0);
        TextView tv = contentLayout.getMessageView();
        tv.setTextColor(color);
        return this;
    }

    public Snackbar setActionTextColor(ColorStateList colors) {
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) this.view.getChildAt(0);
        TextView tv = contentLayout.getActionView();
        tv.setTextColor(colors);
        return this;
    }

    public Snackbar setMaxInlineActionWidth(int width) {
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) this.view.getChildAt(0);
        contentLayout.setMaxInlineActionWidth(width);
        return this;
    }

    public Snackbar setActionTextColor(int color) {
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) this.view.getChildAt(0);
        TextView tv = contentLayout.getActionView();
        tv.setTextColor(color);
        return this;
    }

    public Snackbar setBackgroundTint(int color) {
        return setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public Snackbar setBackgroundTintList(ColorStateList colorStateList) {
        this.view.setBackgroundTintList(colorStateList);
        return this;
    }

    public Snackbar setBackgroundTintMode(PorterDuff.Mode mode) {
        this.view.setBackgroundTintMode(mode);
        return this;
    }

    @Deprecated
    public Snackbar setCallback(Callback callback) {
        BaseTransientBottomBar.BaseCallback<Snackbar> baseCallback = this.callback;
        if (baseCallback != null) {
            removeCallback(baseCallback);
        }
        if (callback != null) {
            addCallback(callback);
        }
        this.callback = callback;
        return this;
    }

    /* loaded from: classes.dex */
    public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout {
        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackground(Drawable drawable) {
            super.setBackground(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundDrawable(Drawable drawable) {
            super.setBackgroundDrawable(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintList(ColorStateList colorStateList) {
            super.setBackgroundTintList(colorStateList);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintMode(PorterDuff.Mode mode) {
            super.setBackgroundTintMode(mode);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener) {
            super.setOnClickListener(onClickListener);
        }

        public SnackbarLayout(Context context) {
            super(context);
        }

        public SnackbarLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int childCount = getChildCount();
            int availableWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child.getLayoutParams().width == -1) {
                    child.measure(View.MeasureSpec.makeMeasureSpec(availableWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), BasicMeasure.EXACTLY));
                }
            }
        }
    }
}
