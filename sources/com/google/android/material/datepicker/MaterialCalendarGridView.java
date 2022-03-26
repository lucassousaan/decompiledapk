package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MaterialCalendarGridView extends GridView {
    private final Calendar dayCompute;
    private final boolean nestedScrollable;

    public MaterialCalendarGridView(Context context) {
        this(context, null);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.dayCompute = UtcDates.getUtcCalendar();
        if (MaterialDatePicker.isFullscreen(getContext())) {
            setNextFocusLeftId(R.id.cancel_button);
            setNextFocusRightId(R.id.confirm_button);
        }
        this.nestedScrollable = MaterialDatePicker.isNestedScrollable(getContext());
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.google.android.material.datepicker.MaterialCalendarGridView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCollectionInfo(null);
            }
        });
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter2().notifyDataSetChanged();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int position) {
        if (position < getAdapter2().firstPositionInMonth()) {
            super.setSelection(getAdapter2().firstPositionInMonth());
        } else {
            super.setSelection(position);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean result = super.onKeyDown(keyCode, event);
        if (!result) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter2().firstPositionInMonth()) {
            return true;
        }
        if (19 != keyCode) {
            return false;
        }
        setSelection(getAdapter2().firstPositionInMonth());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    /* renamed from: getAdapter */
    public ListAdapter getAdapter2() {
        return (MonthAdapter) super.getAdapter();
    }

    @Override // android.widget.GridView, android.widget.AbsListView
    public final void setAdapter(ListAdapter adapter) {
        if (adapter instanceof MonthAdapter) {
            super.setAdapter(adapter);
            return;
        }
        throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()));
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        int rangeHighlightStart;
        int firstHighlightPosition;
        DateSelector<?> dateSelector;
        int rangeHighlightEnd;
        int lastHighlightPosition;
        int right;
        int left;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        MonthAdapter monthAdapter = getAdapter2();
        DateSelector<?> dateSelector2 = monthAdapter.dateSelector;
        CalendarStyle calendarStyle = monthAdapter.calendarStyle;
        Long firstOfMonth = monthAdapter.getItem(monthAdapter.firstPositionInMonth());
        Long lastOfMonth = monthAdapter.getItem(monthAdapter.lastPositionInMonth());
        Iterator<Pair<Long, Long>> it = dateSelector2.getSelectedRanges().iterator();
        while (it.hasNext()) {
            Pair<Long, Long> range = it.next();
            if (range.first == null) {
                materialCalendarGridView = this;
            } else if (range.second != null) {
                long startItem = range.first.longValue();
                long endItem = range.second.longValue();
                if (!skipMonth(firstOfMonth, lastOfMonth, Long.valueOf(startItem), Long.valueOf(endItem))) {
                    boolean isRtl = ViewUtils.isLayoutRtl(this);
                    if (startItem < firstOfMonth.longValue()) {
                        firstHighlightPosition = monthAdapter.firstPositionInMonth();
                        if (monthAdapter.isFirstInRow(firstHighlightPosition)) {
                            rangeHighlightStart = 0;
                        } else if (!isRtl) {
                            rangeHighlightStart = materialCalendarGridView.getChildAt(firstHighlightPosition - 1).getRight();
                        } else {
                            rangeHighlightStart = materialCalendarGridView.getChildAt(firstHighlightPosition - 1).getLeft();
                        }
                    } else {
                        materialCalendarGridView.dayCompute.setTimeInMillis(startItem);
                        firstHighlightPosition = monthAdapter.dayToPosition(materialCalendarGridView.dayCompute.get(5));
                        rangeHighlightStart = horizontalMidPoint(materialCalendarGridView.getChildAt(firstHighlightPosition));
                    }
                    if (endItem > lastOfMonth.longValue()) {
                        dateSelector = dateSelector2;
                        lastHighlightPosition = Math.min(monthAdapter.lastPositionInMonth(), getChildCount() - 1);
                        if (monthAdapter.isLastInRow(lastHighlightPosition)) {
                            rangeHighlightEnd = getWidth();
                        } else if (!isRtl) {
                            rangeHighlightEnd = materialCalendarGridView.getChildAt(lastHighlightPosition).getRight();
                        } else {
                            rangeHighlightEnd = materialCalendarGridView.getChildAt(lastHighlightPosition).getLeft();
                        }
                    } else {
                        dateSelector = dateSelector2;
                        materialCalendarGridView.dayCompute.setTimeInMillis(endItem);
                        lastHighlightPosition = monthAdapter.dayToPosition(materialCalendarGridView.dayCompute.get(5));
                        rangeHighlightEnd = horizontalMidPoint(materialCalendarGridView.getChildAt(lastHighlightPosition));
                    }
                    int firstRow = (int) monthAdapter.getItemId(firstHighlightPosition);
                    int row = firstRow;
                    for (int lastRow = (int) monthAdapter.getItemId(lastHighlightPosition); row <= lastRow; lastRow = lastRow) {
                        int firstPositionInRow = row * getNumColumns();
                        int firstRow2 = (firstPositionInRow + getNumColumns()) - 1;
                        View firstView = materialCalendarGridView.getChildAt(firstPositionInRow);
                        int top = firstView.getTop() + calendarStyle.day.getTopInset();
                        int bottom = firstView.getBottom() - calendarStyle.day.getBottomInset();
                        if (!isRtl) {
                            left = firstPositionInRow > firstHighlightPosition ? 0 : rangeHighlightStart;
                            right = lastHighlightPosition > firstRow2 ? getWidth() : rangeHighlightEnd;
                        } else {
                            left = lastHighlightPosition > firstRow2 ? 0 : rangeHighlightEnd;
                            right = firstPositionInRow > firstHighlightPosition ? getWidth() : rangeHighlightStart;
                        }
                        canvas.drawRect(left, top, right, bottom, calendarStyle.rangeFill);
                        row++;
                        materialCalendarGridView = this;
                        monthAdapter = monthAdapter;
                        firstRow = firstRow;
                        lastHighlightPosition = lastHighlightPosition;
                    }
                    materialCalendarGridView = this;
                    firstOfMonth = firstOfMonth;
                    dateSelector2 = dateSelector;
                    lastOfMonth = lastOfMonth;
                    it = it;
                }
            }
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.nestedScrollable) {
            int expandSpec = View.MeasureSpec.makeMeasureSpec(ViewCompat.MEASURED_SIZE_MASK, Integer.MIN_VALUE);
            super.onMeasure(widthMeasureSpec, expandSpec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (gainFocus) {
            gainFocus(direction, previouslyFocusedRect);
        } else {
            super.onFocusChanged(false, direction, previouslyFocusedRect);
        }
    }

    private void gainFocus(int direction, Rect previouslyFocusedRect) {
        if (direction == 33) {
            setSelection(getAdapter2().lastPositionInMonth());
        } else if (direction == 130) {
            setSelection(getAdapter2().firstPositionInMonth());
        } else {
            super.onFocusChanged(true, direction, previouslyFocusedRect);
        }
    }

    private static boolean skipMonth(Long firstOfMonth, Long lastOfMonth, Long startDay, Long endDay) {
        return firstOfMonth == null || lastOfMonth == null || startDay == null || endDay == null || startDay.longValue() > lastOfMonth.longValue() || endDay.longValue() < firstOfMonth.longValue();
    }

    private static int horizontalMidPoint(View view) {
        return view.getLeft() + (view.getWidth() / 2);
    }
}
