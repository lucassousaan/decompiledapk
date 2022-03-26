package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.Optimizer;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    /* loaded from: classes.dex */
    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    /* loaded from: classes.dex */
    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer layout) {
        this.mVariableDimensionsWidgets.clear();
        int childCount = layout.mChildren.size();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget widget = layout.mChildren.get(i);
            if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mVariableDimensionsWidgets.add(widget);
            }
        }
        layout.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    private void measureChildren(ConstraintWidgetContainer layout) {
        int childCount = layout.mChildren.size();
        boolean optimize = layout.optimizeFor(64);
        Measurer measurer = layout.getMeasurer();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget child = layout.mChildren.get(i);
            if (!(child instanceof Guideline) && !(child instanceof Barrier) && !child.isInVirtualLayout() && (!optimize || child.horizontalRun == null || child.verticalRun == null || !child.horizontalRun.dimension.resolved || !child.verticalRun.dimension.resolved)) {
                boolean skip = false;
                ConstraintWidget.DimensionBehaviour widthBehavior = child.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour heightBehavior = child.getDimensionBehaviour(1);
                if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth != 1 && heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight != 1) {
                    skip = true;
                }
                if (!skip && layout.optimizeFor(1) && !(child instanceof VirtualLayout)) {
                    if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth == 0 && heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if (heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight == 0 && widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if ((widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && child.mDimensionRatio > 0.0f) {
                        skip = true;
                    }
                }
                if (!skip) {
                    measure(measurer, child, Measure.SELF_DIMENSIONS);
                    if (layout.mMetrics != null) {
                        layout.mMetrics.measuredWidgets++;
                    }
                }
            }
        }
        measurer.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer layout, String reason, int w, int h) {
        int minWidth = layout.getMinWidth();
        int minHeight = layout.getMinHeight();
        layout.setMinWidth(0);
        layout.setMinHeight(0);
        layout.setWidth(w);
        layout.setHeight(h);
        layout.setMinWidth(minWidth);
        layout.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer layout, int optimizationLevel, int paddingX, int paddingY, int widthMode, int widthSize, int heightMode, int heightSize, int lastMeasureWidth, int lastMeasureHeight) {
        boolean ratio;
        long layoutTime;
        int heightSize2;
        int widthSize2;
        int optimizations;
        int startingWidth;
        boolean containerWrapWidth;
        Measurer measurer;
        int j;
        int sizeDependentWidgetsCount;
        int measureStrategy;
        boolean optimize;
        int measureStrategy2;
        Measurer measurer2;
        int startingWidth2;
        int optimizations2;
        boolean needSolverPass;
        boolean measuredWidth;
        boolean z;
        boolean optimize2;
        Measurer measurer3 = layout.getMeasurer();
        int childCount = layout.mChildren.size();
        int startingWidth3 = layout.getWidth();
        int startingHeight = layout.getHeight();
        boolean optimizeWrap = Optimizer.enabled(optimizationLevel, 128);
        boolean optimize3 = optimizeWrap || Optimizer.enabled(optimizationLevel, 64);
        if (optimize3) {
            int i = 0;
            while (i < childCount) {
                ConstraintWidget child = layout.mChildren.get(i);
                boolean matchWidth = child.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean matchHeight = child.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean ratio2 = matchWidth && matchHeight && child.getDimensionRatio() > 0.0f;
                if (!child.isInHorizontalChain() || !ratio2) {
                    if (child.isInVerticalChain() && ratio2) {
                        ratio = false;
                        break;
                    }
                    boolean matchWidth2 = child instanceof VirtualLayout;
                    if (matchWidth2) {
                        ratio = false;
                        break;
                    } else if (child.isInHorizontalChain() || child.isInVerticalChain()) {
                        ratio = false;
                        break;
                    } else {
                        i++;
                        optimize3 = optimize3;
                    }
                } else {
                    ratio = false;
                    break;
                }
            }
            optimize2 = optimize3;
        } else {
            optimize2 = optimize3;
        }
        ratio = optimize2;
        if (!ratio || LinearSystem.sMetrics == null) {
            layoutTime = 0;
        } else {
            Metrics metrics = LinearSystem.sMetrics;
            layoutTime = 0;
            long layoutTime2 = metrics.measures;
            metrics.measures = layoutTime2 + 1;
        }
        boolean allSolved = false;
        boolean optimize4 = ((widthMode == 1073741824 && heightMode == 1073741824) || optimizeWrap) & ratio;
        int computations = 0;
        if (optimize4) {
            widthSize2 = Math.min(layout.getMaxWidth(), widthSize);
            heightSize2 = Math.min(layout.getMaxHeight(), heightSize);
            if (widthMode == 1073741824 && layout.getWidth() != widthSize2) {
                layout.setWidth(widthSize2);
                layout.invalidateGraph();
            }
            if (heightMode == 1073741824 && layout.getHeight() != heightSize2) {
                layout.setHeight(heightSize2);
                layout.invalidateGraph();
            }
            if (widthMode == 1073741824 && heightMode == 1073741824) {
                allSolved = layout.directMeasure(optimizeWrap);
                computations = 2;
                z = true;
            } else {
                allSolved = layout.directMeasureSetup(optimizeWrap);
                if (widthMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 0);
                    computations = 0 + 1;
                }
                if (heightMode == 1073741824) {
                    z = true;
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 1);
                    computations++;
                } else {
                    z = true;
                }
            }
            if (allSolved) {
                if (widthMode != 1073741824) {
                    z = false;
                }
                layout.updateFromRuns(z, heightMode == 1073741824);
            }
        } else {
            widthSize2 = widthSize;
            heightSize2 = heightSize;
        }
        if (!allSolved || computations != 2) {
            int optimizations3 = layout.getOptimizationLevel();
            if (childCount > 0) {
                measureChildren(layout);
            }
            updateHierarchy(layout);
            int sizeDependentWidgetsCount2 = this.mVariableDimensionsWidgets.size();
            if (childCount > 0) {
                solveLinearSystem(layout, "First pass", startingWidth3, startingHeight);
            }
            if (sizeDependentWidgetsCount2 > 0) {
                boolean containerWrapWidth2 = layout.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean containerWrapHeight = layout.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                int minWidth = Math.max(layout.getWidth(), this.constraintWidgetContainer.getMinWidth());
                int minHeight = Math.max(layout.getHeight(), this.constraintWidgetContainer.getMinHeight());
                boolean needSolverPass2 = false;
                int widthSize3 = 0;
                int minWidth2 = minWidth;
                int minHeight2 = minHeight;
                while (widthSize3 < sizeDependentWidgetsCount2) {
                    ConstraintWidget widget = this.mVariableDimensionsWidgets.get(widthSize3);
                    if (!(widget instanceof VirtualLayout)) {
                        measurer2 = measurer3;
                        optimizations2 = optimizations3;
                        startingWidth2 = startingWidth3;
                    } else {
                        int preWidth = widget.getWidth();
                        optimizations2 = optimizations3;
                        int preHeight = widget.getHeight();
                        startingWidth2 = startingWidth3;
                        boolean needSolverPass3 = needSolverPass2 | measure(measurer3, widget, Measure.TRY_GIVEN_DIMENSIONS);
                        if (layout.mMetrics != null) {
                            needSolverPass = needSolverPass3;
                            measurer2 = measurer3;
                            layout.mMetrics.measuredMatchWidgets++;
                        } else {
                            needSolverPass = needSolverPass3;
                            measurer2 = measurer3;
                        }
                        int measuredWidth2 = widget.getWidth();
                        int measuredHeight = widget.getHeight();
                        if (measuredWidth2 != preWidth) {
                            widget.setWidth(measuredWidth2);
                            if (containerWrapWidth2 && widget.getRight() > minWidth2) {
                                int w = widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                minWidth2 = Math.max(minWidth2, w);
                            }
                            measuredWidth = true;
                        } else {
                            measuredWidth = needSolverPass;
                        }
                        if (measuredHeight != preHeight) {
                            widget.setHeight(measuredHeight);
                            if (containerWrapHeight && widget.getBottom() > minHeight2) {
                                int h = widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                minHeight2 = Math.max(minHeight2, h);
                            }
                            measuredWidth = true;
                        }
                        VirtualLayout virtualLayout = (VirtualLayout) widget;
                        needSolverPass2 = measuredWidth | virtualLayout.needSolverPass();
                    }
                    widthSize3++;
                    heightSize2 = heightSize2;
                    computations = computations;
                    optimizations3 = optimizations2;
                    startingWidth3 = startingWidth2;
                    measurer3 = measurer2;
                }
                Measurer measurer4 = measurer3;
                optimizations = optimizations3;
                int maxIterations = 2;
                int j2 = 0;
                while (true) {
                    if (j2 >= maxIterations) {
                        startingWidth = startingWidth3;
                        break;
                    }
                    int i2 = 0;
                    while (i2 < sizeDependentWidgetsCount2) {
                        ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i2);
                        if ((!(widget2 instanceof Helper) || (widget2 instanceof VirtualLayout)) && !(widget2 instanceof Guideline) && widget2.getVisibility() != 8 && ((!optimize4 || !widget2.horizontalRun.dimension.resolved || !widget2.verticalRun.dimension.resolved) && !(widget2 instanceof VirtualLayout))) {
                            int preWidth2 = widget2.getWidth();
                            int preHeight2 = widget2.getHeight();
                            optimize = optimize4;
                            int preBaselineDistance = widget2.getBaselineDistance();
                            int measureStrategy3 = Measure.TRY_GIVEN_DIMENSIONS;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                            if (j2 == maxIterations - 1) {
                                int measureStrategy4 = Measure.USE_GIVEN_DIMENSIONS;
                                measureStrategy2 = measureStrategy4;
                            } else {
                                measureStrategy2 = measureStrategy3;
                            }
                            measureStrategy = maxIterations;
                            boolean hasMeasure = measure(measurer4, widget2, measureStrategy2);
                            boolean needSolverPass4 = needSolverPass2 | hasMeasure;
                            if (layout.mMetrics != null) {
                                measurer = measurer4;
                                j = j2;
                                layout.mMetrics.measuredMatchWidgets++;
                            } else {
                                measurer = measurer4;
                                j = j2;
                            }
                            int measuredWidth3 = widget2.getWidth();
                            int measuredHeight2 = widget2.getHeight();
                            if (measuredWidth3 != preWidth2) {
                                widget2.setWidth(measuredWidth3);
                                if (!containerWrapWidth2 || widget2.getRight() <= minWidth2) {
                                    containerWrapWidth = containerWrapWidth2;
                                } else {
                                    containerWrapWidth = containerWrapWidth2;
                                    int w2 = widget2.getRight() + widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                    minWidth2 = Math.max(minWidth2, w2);
                                }
                                needSolverPass4 = true;
                            } else {
                                containerWrapWidth = containerWrapWidth2;
                            }
                            if (measuredHeight2 != preHeight2) {
                                widget2.setHeight(measuredHeight2);
                                if (containerWrapHeight && widget2.getBottom() > minHeight2) {
                                    int h2 = widget2.getBottom() + widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                    minHeight2 = Math.max(minHeight2, h2);
                                }
                                needSolverPass4 = true;
                            }
                            if (!widget2.hasBaseline() || preBaselineDistance == widget2.getBaselineDistance()) {
                                needSolverPass2 = needSolverPass4;
                            } else {
                                needSolverPass2 = true;
                            }
                        } else {
                            containerWrapWidth = containerWrapWidth2;
                            measureStrategy = maxIterations;
                            j = j2;
                            optimize = optimize4;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                            measurer = measurer4;
                        }
                        i2++;
                        optimize4 = optimize;
                        maxIterations = measureStrategy;
                        sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                        j2 = j;
                        measurer4 = measurer;
                        containerWrapWidth2 = containerWrapWidth;
                    }
                    if (!needSolverPass2) {
                        startingWidth = startingWidth3;
                        break;
                    }
                    solveLinearSystem(layout, "intermediate pass", startingWidth3, startingHeight);
                    needSolverPass2 = false;
                    j2++;
                    optimize4 = optimize4;
                    maxIterations = maxIterations;
                    sizeDependentWidgetsCount2 = sizeDependentWidgetsCount2;
                    measurer4 = measurer4;
                    containerWrapWidth2 = containerWrapWidth2;
                }
                if (needSolverPass2) {
                    solveLinearSystem(layout, "2nd pass", startingWidth, startingHeight);
                    boolean needSolverPass5 = false;
                    if (layout.getWidth() < minWidth2) {
                        layout.setWidth(minWidth2);
                        needSolverPass5 = true;
                    }
                    if (layout.getHeight() < minHeight2) {
                        layout.setHeight(minHeight2);
                        needSolverPass5 = true;
                    }
                    if (needSolverPass5) {
                        solveLinearSystem(layout, "3rd pass", startingWidth, startingHeight);
                    }
                }
            } else {
                optimizations = optimizations3;
            }
            layout.setOptimizationLevel(optimizations);
        }
        return layoutTime;
    }

    private boolean measure(Measurer measurer, ConstraintWidget widget, int measureStrategy) {
        this.mMeasure.horizontalBehavior = widget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = widget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = widget.getWidth();
        this.mMeasure.verticalDimension = widget.getHeight();
        this.mMeasure.measuredNeedsSolverPass = false;
        this.mMeasure.measureStrategy = measureStrategy;
        boolean horizontalMatchConstraints = this.mMeasure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalMatchConstraints = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean horizontalUseRatio = horizontalMatchConstraints && widget.mDimensionRatio > 0.0f;
        boolean verticalUseRatio = verticalMatchConstraints && widget.mDimensionRatio > 0.0f;
        if (horizontalUseRatio && widget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (verticalUseRatio && widget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.measureStrategy = Measure.SELF_DIMENSIONS;
        return this.mMeasure.measuredNeedsSolverPass;
    }
}
