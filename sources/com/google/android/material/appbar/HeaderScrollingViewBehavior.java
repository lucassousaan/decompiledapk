package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.badge.BadgeDrawable;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    private int overlayTop;
    final Rect tempRect1 = new Rect();
    final Rect tempRect2 = new Rect();
    private int verticalLayoutGap = 0;

    abstract View findFirstDependency(List<View> list);

    public HeaderScrollingViewBehavior() {
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        WindowInsetsCompat parentInsets;
        int childLpHeight = child.getLayoutParams().height;
        if (childLpHeight != -1 && childLpHeight != -2) {
            return false;
        }
        List<View> dependencies = parent.getDependencies(child);
        View header = findFirstDependency(dependencies);
        if (header == null) {
            return false;
        }
        int availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
        if (availableHeight <= 0) {
            availableHeight = parent.getHeight();
        } else if (ViewCompat.getFitsSystemWindows(header) && (parentInsets = parent.getLastWindowInsets()) != null) {
            availableHeight += parentInsets.getSystemWindowInsetTop() + parentInsets.getSystemWindowInsetBottom();
        }
        int height = getScrollRange(header) + availableHeight;
        int headerHeight = header.getMeasuredHeight();
        if (shouldHeaderOverlapScrollingChild()) {
            child.setTranslationY(-headerHeight);
        } else {
            height -= headerHeight;
        }
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, childLpHeight == -1 ? BasicMeasure.EXACTLY : Integer.MIN_VALUE);
        parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        List<View> dependencies = parent.getDependencies(child);
        View header = findFirstDependency(dependencies);
        if (header != null) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            Rect available = this.tempRect1;
            available.set(parent.getPaddingLeft() + lp.leftMargin, header.getBottom() + lp.topMargin, (parent.getWidth() - parent.getPaddingRight()) - lp.rightMargin, ((parent.getHeight() + header.getBottom()) - parent.getPaddingBottom()) - lp.bottomMargin);
            WindowInsetsCompat parentInsets = parent.getLastWindowInsets();
            if (parentInsets != null && ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
                available.left += parentInsets.getSystemWindowInsetLeft();
                available.right -= parentInsets.getSystemWindowInsetRight();
            }
            Rect out = this.tempRect2;
            GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(), child.getMeasuredHeight(), available, out, layoutDirection);
            int overlap = getOverlapPixelsForOffset(header);
            child.layout(out.left, out.top - overlap, out.right, out.bottom - overlap);
            this.verticalLayoutGap = out.top - header.getBottom();
            return;
        }
        super.layoutChild(parent, child, layoutDirection);
        this.verticalLayoutGap = 0;
    }

    protected boolean shouldHeaderOverlapScrollingChild() {
        return false;
    }

    float getOverlapRatioForOffset(View header) {
        return 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getOverlapPixelsForOffset(View header) {
        if (this.overlayTop == 0) {
            return 0;
        }
        float overlapRatioForOffset = getOverlapRatioForOffset(header);
        int i = this.overlayTop;
        return MathUtils.clamp((int) (overlapRatioForOffset * i), 0, i);
    }

    private static int resolveGravity(int gravity) {
        return gravity == 0 ? BadgeDrawable.TOP_START : gravity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getScrollRange(View v) {
        return v.getMeasuredHeight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getVerticalLayoutGap() {
        return this.verticalLayoutGap;
    }

    public final void setOverlayTop(int overlayTop) {
        this.overlayTop = overlayTop;
    }

    public final int getOverlayTop() {
        return this.overlayTop;
    }
}
