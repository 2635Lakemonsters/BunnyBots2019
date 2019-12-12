package frc.robot.model;

import org.frcteam2910.common.math.Rotation2;
import org.frcteam2910.common.math.Vector2;
import static frc.robot.model.Path2DPoint.SlopeMethod.SLOPE_SMOOTH;
import static frc.robot.model.Path2DPoint.SlopeMethod.SLOPE_MANUAL;

public class Path2DPoint {
    public static transient final int STEPS = 600;

    private Vector2 m_position;
    private Vector2 m_prevAngleAndMagnitude = new Vector2(0, 1.9);
    private Vector2 m_nextAngleAndMagnitude = new Vector2(0, 1.9);
    private Vector2 m_prevTangent = new Vector2(0, 0);
    private Vector2 m_nextTangent = new Vector2(0, 0);
    private SlopeMethod m_prevSlopeMethod = SlopeMethod.SLOPE_SMOOTH;
    private SlopeMethod m_nextSlopeMethod = SlopeMethod.SLOPE_SMOOTH;
    private Path2DPoint m_nextPoint = null;

    private transient boolean m_bTangentsDirty = true;
    private transient boolean m_bCoefficientsDirty = true;
    private transient CubicCoefficients1D m_xCoeff;
    private transient CubicCoefficients1D m_yCoeff;
    private transient double m_segmentLength = 0;
    private transient double partialLength = -1, prevPartialLength;
    private transient Path2DCurve m_path2DCurve = null;
    private transient Path2DPoint m_prevPoint = null;

    public Path2DPoint(double x, double y) {
        m_position = new Vector2(x, y);
    }

    public Path2DPoint() {
        this(0, 0);
    }

    public enum PointType {
        POINT, PREV_TANGENT, NEXT_TANGENT
    }

    public void onPositionChanged() {
        getPath2DCurve().onPositionChanged();  // tell the path too

        setTangentsDirty(true);
        setCoefficientsDirty(true);

        if (getPrevPoint() != null) {
            getPrevPoint().setTangentsDirty(true);
            getPrevPoint().setCoefficientsDirty(true);

            if (getNextPoint() == null && getPrevPoint().getPrevPoint() != null) {
                getPrevPoint().getPrevPoint().setCoefficientsDirty(true);  // coefficients two back need recalculated
            }
        }

        if (getNextPoint() != null) {
            getNextPoint().setTangentsDirty(true);
            getNextPoint().setCoefficientsDirty(true);
        }
    }

    public boolean areTangentsDirty() {
        return m_bTangentsDirty;
    }

    public void setTangentsDirty(boolean bTangentsDirty) {
        m_bTangentsDirty = bTangentsDirty;
    }

    public boolean areCoefficientsDirty() {
        return m_bCoefficientsDirty;
    }

    public void setCoefficientsDirty(boolean bCoefficientsDirty) {
        m_bCoefficientsDirty = bCoefficientsDirty;
    }

    public Vector2 getPosition() {
        return m_position;
    }

    public void setPosition(Vector2 position) {
        this.m_position = position;
        onPositionChanged();
    }

    public Vector2 getPrevAngleAndMagnitude() {
        return m_prevAngleAndMagnitude;
    }

    public void setPrevAngleAndMagnitude(Vector2 prevAngleAndMagnitude) {
        m_prevAngleAndMagnitude = new Vector2(prevAngleAndMagnitude.x, prevAngleAndMagnitude.y);
        if (m_prevSlopeMethod == SLOPE_MANUAL) {
            m_prevSlopeMethod = SLOPE_SMOOTH;
            calculateTangents();
            m_prevSlopeMethod = SLOPE_MANUAL;
        }
        onPositionChanged();
    }

    public Vector2 getNextAngleAndMagnitude() {
        return m_nextAngleAndMagnitude;
    }

    public void setNextAngleAndMagnitude(Vector2 nextAngleAndMagnitude) {  // this one takes the angle in world space - stored as an offset
        m_nextAngleAndMagnitude = new Vector2(nextAngleAndMagnitude.x, nextAngleAndMagnitude.y);
        if (m_nextSlopeMethod == SLOPE_MANUAL) {
            m_nextSlopeMethod = SLOPE_SMOOTH;
            calculateTangents();
            m_nextSlopeMethod = SLOPE_MANUAL;
        }
        onPositionChanged();
    }

    public Vector2 getPrevTangent() {
        if (areTangentsDirty())
            calculateTangents();

        return m_prevTangent;
    }

    public void setPrevTangent(Vector2 prevTangent) {
        m_prevTangent = prevTangent;
        if (m_prevPoint!=null) {
            calculatePrevAngleAndMagnitudeFromTangent();
        } else {
            m_nextTangent = new Vector2(prevTangent.x, prevTangent.y);
            m_nextSlopeMethod = SLOPE_MANUAL;
            m_prevTangent = new Vector2(prevTangent.x, prevTangent.y);
            m_prevSlopeMethod = SLOPE_MANUAL;
        }
        onPositionChanged();
    }

    public Vector2 getNextTangent() {
        if (areTangentsDirty())
            calculateTangents();

        return m_nextTangent;
    }

    public void setNextTangent(Vector2 nextTangent) {
        m_nextTangent = nextTangent;
        if (m_nextPoint != null) {
            calculateNextAngleAndMagnitudeFromTangent();
        }
        else {
            m_nextTangent = new Vector2(nextTangent.x, nextTangent.y);
            m_nextSlopeMethod = SLOPE_MANUAL;
            m_prevTangent = new Vector2(nextTangent.x, nextTangent.y);
            m_prevSlopeMethod = SLOPE_MANUAL;
        }
        onPositionChanged();
    }

    private void calculatePrevAngleAndMagnitudeFromTangent() {
        Vector2 prevTangent = new Vector2(m_prevTangent.x, m_prevTangent.y);
        calculateDefaultTangents(true, false);  // determine the default tangents
        double defaultAngle = Math.toDegrees(Math.atan2(m_prevTangent.y, m_prevTangent.x));
        double goalAngle = Math.toDegrees(Math.atan2(prevTangent.y, prevTangent.x));
        double angle = goalAngle - defaultAngle;
        double magnitude = prevTangent.length / m_prevTangent.length;
        m_prevAngleAndMagnitude = new Vector2(angle, magnitude);
        m_prevTangent = prevTangent;
        if (m_prevSlopeMethod==SLOPE_SMOOTH) {
            m_nextAngleAndMagnitude = new Vector2(angle, getNextMagnitude());
        }
    }

    private void calculateNextAngleAndMagnitudeFromTangent() {
        Vector2 nextTangent = new Vector2(m_nextTangent.x, m_nextTangent.y);
        calculateDefaultTangents(false, true);  // determine the default tangents
        double defaultAngle = Math.toDegrees(Math.atan2(m_nextTangent.y, m_nextTangent.x));
        double goalAngle = Math.toDegrees(Math.atan2(nextTangent.y, nextTangent.x));
        double angle = goalAngle - defaultAngle;
        double magnitude = nextTangent.length / m_nextTangent.length;
        m_nextAngleAndMagnitude = new Vector2(angle, magnitude);
        m_nextTangent = nextTangent;
        if (m_nextSlopeMethod==SLOPE_SMOOTH) {
            m_prevAngleAndMagnitude = new Vector2(angle, getPrevMagnitude());
        }
    }

    public Path2DCurve getPath2DCurve() {
        return m_path2DCurve;
    }

    public void setPath2DCurve(Path2DCurve path2DCurve) {
        m_path2DCurve = path2DCurve;
    }

    public Path2DPoint getNextPoint() {
        return m_nextPoint;
    }

    public void setNextPoint(Path2DPoint nextPoint) {
        this.m_nextPoint = nextPoint;
    }

    public Path2DPoint getPrevPoint() {
        return m_prevPoint;
    }

    public void setPrevPoint(Path2DPoint prevPoint) {
        this.m_prevPoint = prevPoint;
    }

    public SlopeMethod getPrevSlopeMethod() {
        return m_prevSlopeMethod;
    }

    public void setPrevSlopeMethod(SlopeMethod slopeMethod) {
        m_prevSlopeMethod = slopeMethod;
        m_bTangentsDirty = true;
    }

    public SlopeMethod getNextSlopeMethod() {
        return m_nextSlopeMethod;
    }

    public void setNextSlopeMethod(SlopeMethod slopeMethod) {
        m_prevSlopeMethod = slopeMethod;
        m_bTangentsDirty = true;
    }

    public double getPrevAngle() {
        return m_prevAngleAndMagnitude.x;
    }

    public double getNextAngle() {
        return m_nextAngleAndMagnitude.x;
    }

    public double getPrevMagnitude() {
        return m_prevAngleAndMagnitude.y;
    }

    public double getNextMagnitude() {
        return m_nextAngleAndMagnitude.y;
    }

    void insertBefore(Path2DPoint newPoint) {
        m_prevPoint = newPoint.m_prevPoint;
        if (newPoint.m_prevPoint != null)
            newPoint.m_prevPoint.m_nextPoint = this;
        newPoint.m_prevPoint = this;
        m_nextPoint = newPoint;
    }

    void insertAfter(Path2DPoint newPoint) {
        m_nextPoint = newPoint.m_nextPoint;
        if (newPoint.m_nextPoint != null)
            newPoint.m_nextPoint.m_prevPoint = this;
        newPoint.m_nextPoint = this;
        m_prevPoint = newPoint;
    }

    private void calculateTangents() {
        setTangentsDirty(false);

        boolean bCalcSmoothPrev = false;
        boolean bCalcSmoothNext = false;

        switch (getPrevSlopeMethod()) {
            case SLOPE_LINEAR:
                if (m_prevPoint != null)
                    m_prevTangent = getPosition().subtract(m_prevPoint.getPosition());
                break;
            case SLOPE_SMOOTH:
                bCalcSmoothPrev = true;
                break;
            case SLOPE_MANUAL:
                // todo: should actually compute the angle and magnitude from the vector here
                break;
        }

        switch (getNextSlopeMethod()) {
            case SLOPE_LINEAR:
                if (m_nextPoint != null)
                    m_nextTangent = m_nextPoint.getPosition().subtract(getPosition());
                break;
            case SLOPE_SMOOTH:
                bCalcSmoothNext = true;
                break;
            case SLOPE_MANUAL:
                // todo: should actually compute the angle and magnitude from the vector here
                break;
        }

        if (bCalcSmoothPrev || bCalcSmoothNext) {
            calculateDefaultTangents(bCalcSmoothPrev, bCalcSmoothNext);

            m_prevTangent = m_prevTangent.scale(getPrevMagnitude())
                .rotateBy(Rotation2.fromDegrees(getPrevAngle()));

            m_nextTangent = m_nextTangent.scale(getNextMagnitude())
                .rotateBy(Rotation2.fromDegrees(getNextAngle()));
        }
    }

    private void calculateDefaultTangents(boolean bCalcSmoothPrev, boolean bCalcSmoothNext) {
        final double defaultSplineDivisor = 2.0;

        if (m_prevPoint != null && m_nextPoint != null) {
            Vector2 delta = m_nextPoint.getPosition().subtract(m_prevPoint.getPosition());
            //double weight = Math.abs(delta.x);  // Bug for paths:  just works for 2d channels I think
            double weight = delta.length;
            if (weight == 0) // if points are on top of one another (no tangents)
            {
                if (bCalcSmoothPrev)
                    m_prevTangent = new Vector2(0, 0);
                if (bCalcSmoothNext)
                    m_nextTangent = new Vector2(0, 0);
            } else {
                delta = delta.scale(1/weight);

                if (bCalcSmoothPrev) {
                    double prevLength = (getPosition().subtract(m_prevPoint.getPosition())).length / defaultSplineDivisor;
                    m_prevTangent = delta.scale(prevLength);
                }
                if (bCalcSmoothNext) {
                    double nextLength = (m_nextPoint.getPosition().subtract(getPosition())).length / defaultSplineDivisor;
                    m_nextTangent = delta.scale(nextLength);
                }
            }
        } else {
            if (m_nextPoint != null) {
                if (bCalcSmoothPrev) {
                    m_prevTangent = m_nextPoint.getPosition().subtract(getPosition());
                    m_prevTangent = m_prevTangent.scale(1.0 / defaultSplineDivisor);
                }
                if (bCalcSmoothNext) {
                    m_nextTangent = m_nextPoint.getPosition().subtract(getPosition());
                    m_nextTangent = m_nextTangent.scale(1.0 / defaultSplineDivisor);
                }
            }

            if (m_prevPoint != null) {
                if (bCalcSmoothPrev) {
                    m_prevTangent = getPosition().subtract(m_prevPoint.getPosition());
                    m_prevTangent = m_prevTangent.scale(1.0 / defaultSplineDivisor);
                }
                if (bCalcSmoothNext) {
                    m_nextTangent = getPosition().subtract(m_prevPoint.getPosition());
                    m_nextTangent = m_nextTangent.scale(1.0 / defaultSplineDivisor);
                }
            }
        }
    }

    public CubicCoefficients1D getXCoefficients() {
        if (areCoefficientsDirty()) {
            calculateCoefficientsAndLength();
        }
        return m_xCoeff;
    }

    public CubicCoefficients1D getYCoefficients() {
        if (areCoefficientsDirty()) {
            calculateCoefficientsAndLength();
        }
        return m_yCoeff;
    }

    private void calculateCoefficientsAndLength() {
        if (areTangentsDirty())
            calculateTangents();
        if (getNextPoint() != null && getNextPoint().areTangentsDirty())
            getNextPoint().calculateTangents();

        setCoefficientsDirty(false);

        double pointax = getPosition().x;
        double pointbx = m_nextPoint.getPosition().x;
        double pointcx = getNextTangent().x;
        double pointdx = m_nextPoint.getPrevTangent().x;
        m_xCoeff = new CubicCoefficients1D(pointax, pointbx, pointcx, pointdx);

        double pointay = getPosition().y;
        double pointby = m_nextPoint.getPosition().y;
        double pointcy = getNextTangent().y;
        double pointdy = m_nextPoint.getPrevTangent().y;
        m_yCoeff = new CubicCoefficients1D(pointay, pointby, pointcy, pointdy);

        // calculate segment length
        Vector2 pos = new Vector2(0, 0);
        m_xCoeff.initFD(STEPS);
        m_yCoeff.initFD(STEPS);
        m_segmentLength = 0;
        Vector2 prevPos = new Vector2(m_xCoeff.getFDValue(), m_yCoeff.getFDValue());

        for (int i = 0; i < STEPS; i++) {
            pos = new Vector2(m_xCoeff.bumpFDFaster(), m_yCoeff.bumpFDFaster());
            m_segmentLength += pos.subtract(prevPos).length;
            prevPos = new Vector2(pos.x, pos.y);
        }
    }

    public double getSegmentLength() {
        if (areCoefficientsDirty()) {
            calculateCoefficientsAndLength();
        }
        return m_segmentLength;
    }

    public Vector2 getPositionAtDistance(double distance) {

        Vector2 pos = new Vector2(0, 0);
        Vector2 prevPos = new Vector2(0, 0);

        if (partialLength < 0 || partialLength > distance) {
            m_xCoeff.initFD(STEPS);
            m_yCoeff.initFD(STEPS);
            partialLength = 0;
        }

        while (partialLength <= distance) {
            pos = new Vector2(m_xCoeff.bumpFD(), m_yCoeff.bumpFD());
            prevPos = new Vector2(m_xCoeff.getFdPrevValue(), m_yCoeff.getFdPrevValue());
            prevPartialLength = partialLength;
            partialLength += pos.subtract(prevPos).length;
        }

        double intoSegment = (distance - prevPartialLength) / (partialLength - prevPartialLength);  // linearly interpolate t based on distance of the surrounding steps

        return prevPos.scale(1.0f - intoSegment).add(pos.scale(intoSegment));
    }

    public Vector2 getTangentAtDistance(double distance) {
        Vector2 pos = new Vector2(0, 0);
        Vector2 prevPos = new Vector2(0, 0);

        if (partialLength < 0 || partialLength > distance) {
            m_xCoeff.initFD(STEPS);
            m_yCoeff.initFD(STEPS);
            partialLength = 0;
        }

        while (partialLength <= distance) {
            pos = new Vector2(m_xCoeff.bumpFD(), m_yCoeff.bumpFD());
            prevPos = new Vector2(m_xCoeff.getFdPrevValue(), m_yCoeff.getFdPrevValue());
            prevPartialLength = partialLength;
            partialLength += pos.subtract(prevPos).length;
        }

        return pos.subtract(prevPos);
    }

    public String toString() {
        String rValue = "";
        rValue += m_position.toString();
        rValue += m_prevAngleAndMagnitude.toString();
        rValue += m_nextAngleAndMagnitude.toString();
        rValue += m_prevTangent.toString();
        rValue += m_nextTangent.toString();
        rValue += m_prevSlopeMethod.toString();
        rValue += m_nextSlopeMethod.toString();
        return rValue;
    }

    public enum SlopeMethod {
        SLOPE_SMOOTH, SLOPE_MANUAL, SLOPE_LINEAR
    }
}