package androidx.constraintlayout.motion.utils;

import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    Arc[] mArcs;
    private final double[] mTime;

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double t, double[] v) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        if (t > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            t = arcArr2[arcArr2.length - 1].mTime2;
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                v[0] = this.mArcs[i].getLinearX(t);
                v[1] = this.mArcs[i].getLinearY(t);
                return;
            } else {
                this.mArcs[i].setPoint(t);
                v[0] = this.mArcs[i].getX();
                v[1] = this.mArcs[i].getY();
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double t, float[] v) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (t > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                t = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                v[0] = (float) this.mArcs[i].getLinearX(t);
                v[1] = (float) this.mArcs[i].getLinearY(t);
                return;
            } else {
                this.mArcs[i].setPoint(t);
                v[0] = (float) this.mArcs[i].getX();
                v[1] = (float) this.mArcs[i].getY();
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getSlope(double t, double[] v) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (t > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                t = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (this.mArcs[i].linear) {
                v[0] = this.mArcs[i].getLinearDX(t);
                v[1] = this.mArcs[i].getLinearDY(t);
                return;
            } else {
                this.mArcs[i].setPoint(t);
                v[0] = this.mArcs[i].getDX();
                v[1] = this.mArcs[i].getDY();
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getPos(double t, int j) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (t > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                t = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return Double.NaN;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (!this.mArcs[i].linear) {
                this.mArcs[i].setPoint(t);
                if (j == 0) {
                    return this.mArcs[i].getX();
                }
                return this.mArcs[i].getY();
            } else if (j == 0) {
                return this.mArcs[i].getLinearX(t);
            } else {
                return this.mArcs[i].getLinearY(t);
            }
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getSlope(double t, int j) {
        if (t < this.mArcs[0].mTime1) {
            t = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        if (t > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            t = arcArr2[arcArr2.length - 1].mTime2;
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return Double.NaN;
            }
            if (t > arcArr3[i].mTime2) {
                i++;
            } else if (!this.mArcs[i].linear) {
                this.mArcs[i].setPoint(t);
                if (j == 0) {
                    return this.mArcs[i].getDX();
                }
                return this.mArcs[i].getDY();
            } else if (j == 0) {
                return this.mArcs[i].getLinearDX(t);
            } else {
                return this.mArcs[i].getLinearDY(t);
            }
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }

    public ArcCurveFit(int[] arcModes, double[] time, double[][] y) {
        this.mTime = time;
        this.mArcs = new Arc[time.length - 1];
        int mode = 1;
        int last = 1;
        int i = 0;
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i < arcArr.length) {
                mode = 2;
                switch (arcModes[i]) {
                    case 0:
                        mode = 3;
                        break;
                    case 1:
                        mode = 1;
                        last = 1;
                        break;
                    case 2:
                        mode = 2;
                        last = 2;
                        break;
                    case 3:
                        mode = last != 1 ? 1 : mode;
                        last = mode;
                        break;
                }
                arcArr[i] = new Arc(mode, time[i], time[i + 1], y[i][0], y[i][1], y[i + 1][0], y[i + 1][1]);
                i++;
            } else {
                return;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];
        boolean linear;
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int mode, double t1, double t2, double x1, double y1, double x2, double y2) {
            double dx;
            double dy;
            double d;
            double d2;
            double d3;
            boolean z = false;
            this.linear = false;
            int i = 1;
            this.mVertical = mode == 1 ? true : z;
            this.mTime1 = t1;
            this.mTime2 = t2;
            this.mOneOverDeltaTime = 1.0d / (t2 - t1);
            if (3 == mode) {
                this.linear = true;
            }
            double dx2 = x2 - x1;
            double dy2 = y2 - y1;
            if (this.linear || Math.abs(dx2) < EPSILON) {
                dx = dx2;
                dy = dy2;
                d2 = x2;
                d = y1;
                d3 = x1;
            } else if (Math.abs(dy2) < EPSILON) {
                dx = dx2;
                dy = dy2;
                d2 = x2;
                d = y1;
                d3 = x1;
            } else {
                this.mLut = new double[101];
                boolean z2 = this.mVertical;
                this.mEllipseA = (z2 ? -1 : i) * dx2;
                this.mEllipseB = (z2 ? 1 : -1) * dy2;
                this.mEllipseCenterX = z2 ? x2 : x1;
                this.mEllipseCenterY = z2 ? y1 : y2;
                buildTable(x1, y1, x2, y2);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                return;
            }
            this.linear = true;
            this.mX1 = d3;
            this.mX2 = d2;
            this.mY1 = d;
            this.mY2 = y2;
            double hypot = Math.hypot(dy, dx);
            this.mArcDistance = hypot;
            this.mArcVelocity = hypot * this.mOneOverDeltaTime;
            double d4 = this.mTime2;
            double d5 = this.mTime1;
            this.mEllipseCenterX = dx / (d4 - d5);
            this.mEllipseCenterY = dy / (d4 - d5);
        }

        void setPoint(double time) {
            double percent = (this.mVertical ? this.mTime2 - time : time - this.mTime1) * this.mOneOverDeltaTime;
            double angle = lookup(percent) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(angle);
            this.mTmpCosAngle = Math.cos(angle);
        }

        double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        double getDX() {
            double vx = this.mEllipseA * this.mTmpCosAngle;
            double vy = (-this.mEllipseB) * this.mTmpSinAngle;
            double norm = this.mArcVelocity / Math.hypot(vx, vy);
            return this.mVertical ? (-vx) * norm : vx * norm;
        }

        double getDY() {
            double vx = this.mEllipseA * this.mTmpCosAngle;
            double vy = (-this.mEllipseB) * this.mTmpSinAngle;
            double norm = this.mArcVelocity / Math.hypot(vx, vy);
            return this.mVertical ? (-vy) * norm : vy * norm;
        }

        public double getLinearX(double t) {
            double t2 = (t - this.mTime1) * this.mOneOverDeltaTime;
            double t3 = this.mX1;
            return t3 + ((this.mX2 - t3) * t2);
        }

        public double getLinearY(double t) {
            double t2 = (t - this.mTime1) * this.mOneOverDeltaTime;
            double t3 = this.mY1;
            return t3 + ((this.mY2 - t3) * t2);
        }

        public double getLinearDX(double t) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double t) {
            return this.mEllipseCenterY;
        }

        double lookup(double v) {
            if (v <= 0.0d) {
                return 0.0d;
            }
            if (v >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double pos = (dArr.length - 1) * v;
            int iv = (int) pos;
            double off = pos - ((int) pos);
            return dArr[iv] + ((dArr[iv + 1] - dArr[iv]) * off);
        }

        private void buildTable(double x1, double y1, double x2, double y2) {
            double[] dArr;
            double[] dArr2;
            double b;
            double a;
            double a2 = x2 - x1;
            double b2 = y1 - y2;
            double lx = 0.0d;
            double ly = 0.0d;
            double dist = 0.0d;
            int i = 0;
            while (true) {
                if (i >= ourPercent.length) {
                    break;
                }
                double dist2 = dist;
                double dist3 = i;
                double angle = Math.toRadians((dist3 * 90.0d) / (dArr.length - 1));
                double s = Math.sin(angle);
                double c = Math.cos(angle);
                double px = a2 * s;
                double py = b2 * c;
                if (i > 0) {
                    a = a2;
                    double a3 = px - lx;
                    b = b2;
                    double b3 = py - ly;
                    double dist4 = Math.hypot(a3, b3) + dist2;
                    ourPercent[i] = dist4;
                    dist2 = dist4;
                } else {
                    a = a2;
                    b = b2;
                }
                lx = px;
                ly = py;
                i++;
                dist = dist2;
                a2 = a;
                b2 = b;
            }
            this.mArcDistance = dist;
            int i2 = 0;
            while (true) {
                double[] dArr3 = ourPercent;
                if (i2 >= dArr3.length) {
                    break;
                }
                dArr3[i2] = dArr3[i2] / dist;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 < this.mLut.length) {
                    double pos = i3 / (dArr2.length - 1);
                    int index = Arrays.binarySearch(ourPercent, pos);
                    if (index >= 0) {
                        this.mLut[i3] = index / (ourPercent.length - 1);
                    } else if (index == -1) {
                        this.mLut[i3] = 0.0d;
                    } else {
                        int p1 = (-index) - 2;
                        int p2 = (-index) - 1;
                        double[] dArr4 = ourPercent;
                        double ans = (p1 + ((pos - dArr4[p1]) / (dArr4[p2] - dArr4[p1]))) / (dArr4.length - 1);
                        this.mLut[i3] = ans;
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }
}
