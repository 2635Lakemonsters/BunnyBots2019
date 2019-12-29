package frc.lib.motion_profiling;

import com.squareup.moshi.Json;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;

public class DrivetrainParameters {

@Json(name = "drivetrain")
private String drivetrain;
@Json(name = "doHeadingCorrection")
private boolean doHeadingCorrection;
@Json(name = "driveTurningP")
private double driveTurningP;
@Json(name = "headingCorrectionI")
private double headingCorrectionI;
@Json(name = "headingCorrectionIDecay")
private double headingCorrectionIDecay;
@Json(name = "headingCorrectionP")
private double headingCorrectionP;
@Json(name = "headingFeedForward")
public double headingFeedForward;
@Json(name = "leftFeedForwardCoefficient")
public double leftFeedForwardCoefficient;
@Json(name = "leftFeedForwardOffset")
public double leftFeedForwardOffset;
@Json(name = "rightFeedForwardCoefficient")
public double rightFeedForwardCoefficient;
@Json(name = "rightFeedForwardOffset")
public double rightFeedForwardOffset;
@Json(name = "scrubFactor")
public double scrubFactor;
@Json(name = "trackWidth")
public double trackWidth;

public String getDrivetrain() {
return drivetrain;
}

public void setDrivetrain(String drivetrain) {
this.drivetrain = drivetrain;
}

public boolean getDoHeadingCorrection() {
return doHeadingCorrection;
}

public void setDoHeadingCorrection(boolean doHeadingCorrection) {
this.doHeadingCorrection = doHeadingCorrection;
}

public double getDriveTurningP() {
return driveTurningP;
}

public void setDriveTurningP(double driveTurningP) {
this.driveTurningP = driveTurningP;
}

public double getHeadingCorrectionI() {
return headingCorrectionI;
}

public void setHeadingCorrectionI(double headingCorrectionI) {
this.headingCorrectionI = headingCorrectionI;
}

public double getHeadingCorrectionIDecay() {
return headingCorrectionIDecay;
}

public void setHeadingCorrectionIDecay(double headingCorrectionIDecay) {
this.headingCorrectionIDecay = headingCorrectionIDecay;
}

public double getHeadingCorrectionP() {
return headingCorrectionP;
}

public void setHeadingCorrectionP(double headingCorrectionP) {
this.headingCorrectionP = headingCorrectionP;
}

public double getHeadingFeedForward() {
return headingFeedForward;
}

public void setHeadingFeedForward(double headingFeedForward) {
this.headingFeedForward = headingFeedForward;
}

public double getLeftFeedForwardCoefficient() {
return leftFeedForwardCoefficient;
}

public void setLeftFeedForwardCoefficient(double leftFeedForwardCoefficient) {
this.leftFeedForwardCoefficient = leftFeedForwardCoefficient;
}

public double getLeftFeedForwardOffset() {
return leftFeedForwardOffset;
}

public void setLeftFeedForwardOffset(double leftFeedForwardOffset) {
this.leftFeedForwardOffset = leftFeedForwardOffset;
}

public double getRightFeedForwardCoefficient() {
return rightFeedForwardCoefficient;
}

public void setRightFeedForwardCoefficient(double rightFeedForwardCoefficient) {
this.rightFeedForwardCoefficient = rightFeedForwardCoefficient;
}

public double getRightFeedForwardOffset() {
return rightFeedForwardOffset;
}

public void setRightFeedForwardOffset(double rightFeedForwardOffset) {
this.rightFeedForwardOffset = rightFeedForwardOffset;
}

public double getScrubFactor() {
return scrubFactor;
}

public void setScrubFactor(double scrubFactor) {
this.scrubFactor = scrubFactor;
}

public double getTrackWidth() {
return trackWidth;
}

public void setTrackWidth(double trackWidth) {
this.trackWidth = trackWidth;
}

public static final PolymorphicJsonAdapterFactory<DrivetrainParameters> getMoshiAdapter() {
  //Intrinsics.checkExpressionValueIsNotNull(PolymorphicJsonAdapterFactory.of(DrivetrainParameters.class, "drivetrain").withSubtype(ArcadeParameters.class, "arcade").withSubtype(SwerveParameters.class, "swerve"), "PolymorphicJsonAdapterFa\"swerve\")");
  return PolymorphicJsonAdapterFactory.of(DrivetrainParameters.class, "drivetrain").withSubtype(ArcadeParameters.class, "arcade").withSubtype(SwerveParameters.class, "swerve");
}

}