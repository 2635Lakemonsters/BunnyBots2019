package frc.lib.motion_profiling;
import com.squareup.moshi.Json;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
// import kotlin.Metadata;
// import kotlin.jvm.internal.DefaultConstructorMarker;
// import org.jetbrains.annotations.NotNull;
// import org.jetbrains.annotations.Nullable;

//@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\000,\n\002\030\002\n\002\030\002\n\000\n\002\020\006\n\002\b\b\n\002\020\013\n\002\b/\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001Bq\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\003\022\006\020\006\032\0020\003\022\006\020\007\032\0020\003\022\006\020\b\032\0020\003\022\b\b\002\020\t\032\0020\003\022\b\b\002\020\n\032\0020\003\022\b\b\002\020\013\032\0020\f\022\b\b\002\020\r\032\0020\003\022\b\b\002\020\016\032\0020\003\022\b\b\002\020\017\032\0020\003\006\002\020\020J\t\020-\032\0020\003H\003J\t\020.\032\0020\003H\003J\t\020/\032\0020\003H\003J\t\0200\032\0020\003H\003J\t\0201\032\0020\003H\003J\t\0202\032\0020\003H\003J\t\0203\032\0020\003H\003J\t\0204\032\0020\003H\003J\t\0205\032\0020\003H\003J\t\0206\032\0020\003H\003J\t\0207\032\0020\003H\003J\t\0208\032\0020\fH\003J\001\0209\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\b\b\002\020\005\032\0020\0032\b\b\002\020\006\032\0020\0032\b\b\002\020\007\032\0020\0032\b\b\002\020\b\032\0020\0032\b\b\002\020\t\032\0020\0032\b\b\002\020\n\032\0020\0032\b\b\002\020\013\032\0020\f2\b\b\002\020\r\032\0020\0032\b\b\002\020\016\032\0020\0032\b\b\002\020\017\032\0020\003H\001J\023\020:\032\0020\f2\b\020;\032\004\030\0010<H\003J\t\020=\032\0020>H\001J\t\020?\032\0020@H\001R\032\020\013\032\0020\fX\016\006\016\n\000\032\004\b\021\020\022\"\004\b\023\020\024R\032\020\t\032\0020\003X\016\006\016\n\000\032\004\b\025\020\026\"\004\b\027\020\030R\032\020\016\032\0020\003X\016\006\016\n\000\032\004\b\031\020\026\"\004\b\032\020\030R\032\020\017\032\0020\003X\016\006\016\n\000\032\004\b\033\020\026\"\004\b\034\020\030R\032\020\r\032\0020\003X\016\006\016\n\000\032\004\b\035\020\026\"\004\b\036\020\030R\032\020\n\032\0020\003X\016\006\016\n\000\032\004\b\037\020\026\"\004\b \020\030R\032\020\005\032\0020\003X\016\006\016\n\000\032\004\b!\020\026\"\004\b\"\020\030R\032\020\006\032\0020\003X\016\006\016\n\000\032\004\b#\020\026\"\004\b$\020\030R\032\020\007\032\0020\003X\016\006\016\n\000\032\004\b%\020\026\"\004\b&\020\030R\032\020\b\032\0020\003X\016\006\016\n\000\032\004\b'\020\026\"\004\b(\020\030R\032\020\004\032\0020\003X\016\006\016\n\000\032\004\b)\020\026\"\004\b*\020\030R\032\020\002\032\0020\003X\016\006\016\n\000\032\004\b+\020\026\"\004\b,\020\030\006A"}, d2 = {"Lorg/team2471/frc/lib/motion_profiling/following/ArcadeParameters;", "Lorg/team2471/frc/lib/motion_profiling/following/DrivetrainParameters;", "trackWidth", "", "scrubFactor", "leftFeedForwardCoefficient", "leftFeedForwardOffset", "rightFeedForwardCoefficient", "rightFeedForwardOffset", "driveTurningP", "headingFeedForward", "doHeadingCorrection", "", "headingCorrectionP", "headingCorrectionI", "headingCorrectionIDecay", "(DDDDDDDDZDDD)V", "getDoHeadingCorrection", "()Z", "setDoHeadingCorrection", "(Z)V", "getDriveTurningP", "()D", "setDriveTurningP", "(D)V", "getHeadingCorrectionI", "setHeadingCorrectionI", "getHeadingCorrectionIDecay", "setHeadingCorrectionIDecay", "getHeadingCorrectionP", "setHeadingCorrectionP", "getHeadingFeedForward", "setHeadingFeedForward", "getLeftFeedForwardCoefficient", "setLeftFeedForwardCoefficient", "getLeftFeedForwardOffset", "setLeftFeedForwardOffset", "getRightFeedForwardCoefficient", "setRightFeedForwardCoefficient", "getRightFeedForwardOffset", "setRightFeedForwardOffset", "getScrubFactor", "setScrubFactor", "getTrackWidth", "setTrackWidth", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "", "meanlib"})
public final class ArcadeParameters extends DrivetrainParameters {
  // @Json(name = "trackWidth")
  // private double trackWidth;
  // @Json(name = "scrubFactor")
  // private double scrubFactor;
  // @Json(name = "leftFeedForwardCoefficient")
  // private double leftFeedForwardCoefficient;
  // @Json(name = "leftFeedForwardOffset")
  // private double leftFeedForwardOffset;
  // @Json(name = "rightFeedForwardCoefficient")
  // private double rightFeedForwardCoefficient;
  // @Json(name = "rightFeedForwardOffset")
  // private double rightFeedForwardOffset;
  //@Json(name = "driveTurningP")
  //private double driveTurningP;
  // @Json(name = "headingFeedForward")
  // private double headingFeedForward;
  // @Json(name = "doHeadingCorrection")
  // private boolean doHeadingCorrection;
  // @Json(name = "headingCorrectionP")
  // private double headingCorrectionP;
  // @Json(name = "headingCorrectionI")
  // private double headingCorrectionI;
  // @Json(name = "headingCorrectionIDecay")
  // private double headingCorrectionIDecay;
  
  public ArcadeParameters(double trackWidth, double scrubFactor, double leftFeedForwardCoefficient, double leftFeedForwardOffset, double rightFeedForwardCoefficient, double rightFeedForwardOffset, double driveTurningP, double headingFeedForward, boolean doHeadingCorrection, double headingCorrectionP, double headingCorrectionI, double headingCorrectionIDecay) {

    this.trackWidth = trackWidth;
    this.scrubFactor = scrubFactor;
    this.leftFeedForwardCoefficient = leftFeedForwardCoefficient;
    this.leftFeedForwardOffset = leftFeedForwardOffset;
    this.rightFeedForwardCoefficient = rightFeedForwardCoefficient;
    this.rightFeedForwardOffset = rightFeedForwardOffset;
    super.setDriveTurningP(driveTurningP);
    this.headingFeedForward = headingFeedForward;
    super.setDoHeadingCorrection(doHeadingCorrection);
   
    setHeadingCorrectionP(headingCorrectionP);
    setHeadingCorrectionI(headingCorrectionI);
    setHeadingCorrectionIDecay(headingCorrectionIDecay);
  }
  
  public final double getTrackWidth() {
    return this.trackWidth;
  }
  
  public final void setTrackWidth(double d) {
    this.trackWidth = d;
  }
  
  public final double getScrubFactor() {
    return this.scrubFactor;
  }
  
  public final void setScrubFactor(double d) {
    this.scrubFactor = d;
  }
  
  public final double getLeftFeedForwardCoefficient() {
    return this.leftFeedForwardCoefficient;
  }
  
  public final void setLeftFeedForwardCoefficient(double d) {
    this.leftFeedForwardCoefficient = d;
  }
  
  public final double getLeftFeedForwardOffset() {
    return this.leftFeedForwardOffset;
  }
  
  public final void setLeftFeedForwardOffset(double d) {
    this.leftFeedForwardOffset = d;
  }
  
  public final double getRightFeedForwardCoefficient() {
    return this.rightFeedForwardCoefficient;
  }
  
  public final void setRightFeedForwardCoefficient(double d) {
    this.rightFeedForwardCoefficient = d;
  }
  
  public final double getRightFeedForwardOffset() {
    return this.rightFeedForwardOffset;
  }
  
  public final void setRightFeedForwardOffset(double d) {
    this.rightFeedForwardOffset = d;
  }
  
  public final double getDriveTurningP() {
    return super.getDriveTurningP();
  }
  
  public final void setDriveTurningP(double d) {
    super.setDriveTurningP(d);
  }
  
  public final double getHeadingFeedForward() {
    return this.headingFeedForward;
  }
  
  public final void setHeadingFeedForward(double d) {
    this.headingFeedForward = d;
  }
  
  public final boolean getDoHeadingCorrection() {
    return super.getDoHeadingCorrection();
  }
  
  public final void setDoHeadingCorrection(boolean b) {
    super.setDoHeadingCorrection(b);
  }
  
  public final double getHeadingCorrectionP() {
    return getHeadingCorrectionP();
  }
  
  public final void setHeadingCorrectionP(double d) {
    setHeadingCorrectionP(d);
  }
  
  public final double getHeadingCorrectionI() {
    return getHeadingCorrectionI();
  }
  
  public final void setHeadingCorrectionI(double d) {
    setHeadingCorrectionI(d);
  }
  
  public final double getHeadingCorrectionIDecay() {
    return getHeadingCorrectionIDecay();
  }
  
  public final void setHeadingCorrectionIDecay(double d) {
    setHeadingCorrectionIDecay(d);
  }
  
  public final double component1() {
    return this.trackWidth;
  }
  
  public final double component2() {
    return this.scrubFactor;
  }
  
  public final double component3() {
    return this.leftFeedForwardCoefficient;
  }
  
  public final double component4() {
    return this.leftFeedForwardOffset;
  }
  
  public final double component5() {
    return this.rightFeedForwardCoefficient;
  }
  
  public final double component6() {
    return this.rightFeedForwardOffset;
  }
  
  public final double component7() {
    return super.getDriveTurningP();
  }
  
  public final double component8() {
    return this.headingFeedForward;
  }
  
  public final boolean component9() {
    return super.getDoHeadingCorrection();
  }
  
  public final double component10() {
    return getHeadingCorrectionP();
  }
  
  public final double component11() {
    return getHeadingCorrectionI();
  }
  
  public final double component12() {
    return getHeadingCorrectionIDecay();
  }
  

  public final ArcadeParameters copy(double trackWidth, double scrubFactor, double leftFeedForwardCoefficient, double leftFeedForwardOffset, double rightFeedForwardCoefficient, double rightFeedForwardOffset, double driveTurningP, double headingFeedForward, boolean doHeadingCorrection, double headingCorrectionP, double headingCorrectionI, double headingCorrectionIDecay) {
    return new ArcadeParameters(trackWidth, scrubFactor, leftFeedForwardCoefficient, leftFeedForwardOffset, rightFeedForwardCoefficient, rightFeedForwardOffset, driveTurningP, headingFeedForward, doHeadingCorrection, headingCorrectionP, headingCorrectionI, headingCorrectionIDecay);
  }
  

  public String toString() {
    return "ArcadeParameters(trackWidth=" + this.trackWidth + ", scrubFactor=" + this.scrubFactor + ", leftFeedForwardCoefficient=" + this.leftFeedForwardCoefficient + ", leftFeedForwardOffset=" + this.leftFeedForwardOffset + ", rightFeedForwardCoefficient=" + this.rightFeedForwardCoefficient + ", rightFeedForwardOffset=" + this.rightFeedForwardOffset + ", driveTurningP=" + super.getDriveTurningP() + ", headingFeedForward=" + this.headingFeedForward + ", doHeadingCorrection=" + super.getDoHeadingCorrection() + ", headingCorrectionP=" + getHeadingCorrectionP() + ", headingCorrectionI=" + getHeadingCorrectionI() + ", headingCorrectionIDecay=" + getHeadingCorrectionIDecay() + ")";
  }
  
  public int hashCode() {
    if (super.getDoHeadingCorrection());
    return ((((((((((Double.hashCode(this.trackWidth) * 31 + Double.hashCode(this.scrubFactor)) * 31 + Double.hashCode(this.leftFeedForwardCoefficient)) * 31 + Double.hashCode(this.leftFeedForwardOffset)) * 31 + Double.hashCode(this.rightFeedForwardCoefficient)) * 31 + Double.hashCode(this.rightFeedForwardOffset)) * 31 + Double.hashCode(super.getDriveTurningP())) * 31 + Double.hashCode(this.headingFeedForward)) * 31 + 1) * 31 + Double.hashCode(getHeadingCorrectionP())) * 31 + Double.hashCode(getHeadingCorrectionI())) * 31 + Double.hashCode(getHeadingCorrectionIDecay());
  }
  
  public boolean equals(Object paramObject) {
    if (this != paramObject) {
      if (paramObject instanceof ArcadeParameters) {
        ArcadeParameters arcadeParameters = (ArcadeParameters)paramObject;
        if (Double.compare(this.trackWidth, arcadeParameters.trackWidth) == 0 && Double.compare(this.scrubFactor, arcadeParameters.scrubFactor) == 0 && Double.compare(this.leftFeedForwardCoefficient, arcadeParameters.leftFeedForwardCoefficient) == 0 && Double.compare(this.leftFeedForwardOffset, arcadeParameters.leftFeedForwardOffset) == 0 && Double.compare(this.rightFeedForwardCoefficient, arcadeParameters.rightFeedForwardCoefficient) == 0 && Double.compare(this.rightFeedForwardOffset, arcadeParameters.rightFeedForwardOffset) == 0 && Double.compare(getDriveTurningP(), arcadeParameters.getDriveTurningP()) == 0 && Double.compare(this.headingFeedForward, arcadeParameters.headingFeedForward) == 0 && ((this.getDoHeadingCorrection() == arcadeParameters.getDoHeadingCorrection())) && Double.compare(getHeadingCorrectionP(), arcadeParameters.getHeadingCorrectionP()) == 0 && Double.compare(getHeadingCorrectionI(), arcadeParameters.getHeadingCorrectionI()) == 0 && Double.compare(this.getHeadingCorrectionIDecay(), arcadeParameters.getHeadingCorrectionIDecay()) == 0)
          return true; 
      } 
    } else {
      return true;
    } 
    return false;
  }
}
