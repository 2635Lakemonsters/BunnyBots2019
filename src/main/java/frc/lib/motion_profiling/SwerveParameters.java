package frc.lib.motion_profiling;

import com.squareup.moshi.Json;



public final class SwerveParameters extends DrivetrainParameters {

  @Json(name = "gyroRateCorrection")
  private final double gyroRateCorrection;
  
  @Json(name = "turningKP")
  private final double turningKP;
  @Json(name = "kpPosition")
  private final double kpPosition;
  @Json(name = "kdPosition")
  private final double kdPosition;
  @Json(name = "kPositionFeedForward")
  private final double kPositionFeedForward;
  @Json(name = "kpHeading")
  private final double kpHeading;
  @Json(name = "kdHeading")
  private final double kdHeading;
  @Json(name = "kHeadingFeedForward")
  private final double kHeadingFeedForward;
  
  public SwerveParameters(double gyroRateCorrection, double turningKP, double kpPosition, double kdPosition, double kPositionFeedForward, double kpHeading, double kdHeading, double kHeadingFeedForward) {

    this.gyroRateCorrection = gyroRateCorrection;
    this.turningKP = turningKP;
    this.kpPosition = kpPosition;
    this.kdPosition = kdPosition;
    this.kPositionFeedForward = kPositionFeedForward;
    this.kpHeading = kpHeading;
    this.kdHeading = kdHeading;
    this.kHeadingFeedForward = kHeadingFeedForward;
  }
  
  public final double getGyroRateCorrection() {
    return this.gyroRateCorrection;
  }
  
  public final double getTurningKP() {
    return this.turningKP;
  }
  
  public final double getKpPosition() {
    return this.kpPosition;
  }
  
  public final double getKdPosition() {
    return this.kdPosition;
  }
  
  public final double getKPositionFeedForward() {
    return this.kPositionFeedForward;
  }
  
  public final double getKpHeading() {
    return this.kpHeading;
  }
  
  public final double getKdHeading() {
    return this.kdHeading;
  }
  
  public final double getKHeadingFeedForward() {
    return this.kHeadingFeedForward;
  }
  
  public final double component1() {
    return this.gyroRateCorrection;
  }
  
  public final double component2() {
    return this.turningKP;
  }
  
  public final double component3() {
    return this.kpPosition;
  }
  
  public final double component4() {
    return this.kdPosition;
  }
  
  public final double component5() {
    return this.kPositionFeedForward;
  }
  
  public final double component6() {
    return this.kpHeading;
  }
  
  public final double component7() {
    return this.kdHeading;
  }
  
  public final double component8() {
    return this.kHeadingFeedForward;
  }
  

  public final SwerveParameters copy(double gyroRateCorrection, double turningKP, double kpPosition, double kdPosition, double kPositionFeedForward, double kpHeading, double kdHeading, double kHeadingFeedForward) {
    return new SwerveParameters(gyroRateCorrection, turningKP, kpPosition, kdPosition, kPositionFeedForward, kpHeading, kdHeading, kHeadingFeedForward);
  }
  

  public String toString() {
    return "SwerveParameters(gyroRateCorrection=" + this.gyroRateCorrection + ", turningKP=" + this.turningKP + ", kpPosition=" + this.kpPosition + ", kdPosition=" + this.kdPosition + ", kPositionFeedForward=" + this.kPositionFeedForward + ", kpHeading=" + this.kpHeading + ", kdHeading=" + this.kdHeading + ", kHeadingFeedForward=" + this.kHeadingFeedForward + ")";
  }
  
  public int hashCode() {
    return ((((((Double.hashCode(this.gyroRateCorrection) * 31 + Double.hashCode(this.turningKP)) * 31 + Double.hashCode(this.kpPosition)) * 31 + Double.hashCode(this.kdPosition)) * 31 + Double.hashCode(this.kPositionFeedForward)) * 31 + Double.hashCode(this.kpHeading)) * 31 + Double.hashCode(this.kdHeading)) * 31 + Double.hashCode(this.kHeadingFeedForward);
  }
  
  public boolean equals(Object paramObject) {
    if (this != paramObject) {
      if (paramObject instanceof SwerveParameters) {
        SwerveParameters swerveParameters = (SwerveParameters)paramObject;
        if (Double.compare(this.gyroRateCorrection, swerveParameters.gyroRateCorrection) == 0 && Double.compare(this.turningKP, swerveParameters.turningKP) == 0 && Double.compare(this.kpPosition, swerveParameters.kpPosition) == 0 && Double.compare(this.kdPosition, swerveParameters.kdPosition) == 0 && Double.compare(this.kPositionFeedForward, swerveParameters.kPositionFeedForward) == 0 && Double.compare(this.kpHeading, swerveParameters.kpHeading) == 0 && Double.compare(this.kdHeading, swerveParameters.kdHeading) == 0 && Double.compare(this.kHeadingFeedForward, swerveParameters.kHeadingFeedForward) == 0)
          return true; 
      } 
    } else {
      return true;
    } 
    return false;
  }
}
