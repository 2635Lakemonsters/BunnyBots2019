package frc.lib.math;

import com.squareup.moshi.Json;


public final class Vector2 {

  @Json(name = "x")
  private double x;
  @Json(name = "y")
  private double y;
  
  public final double getX() {
    return this.x;
  }
  
  public final void setX(double d) {
    this.x = d;
  }
  
  public final double getY() {
    return this.y;
  }
  
  public final void setY(double d) {
    this.y = d;
  }
  
  public Vector2(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public final double getLength() {
    return Math.sqrt(dot(this));
  }
  
  public final double getAngle() {
    return Math.atan2(this.x, this.y);
  }
  

  public final Vector2 rotateRadians(double radians) {
    double c = Math.cos(radians);
    double s = Math.sin(radians);
    return new Vector2(this.x * c - this.y * s, this.x * s + this.y * c);
  }
  
  public static final class Companion {
    private Companion() {}
    

    public static final Vector2 add( Vector2 firstVector,  Vector2 secondVector) {
      //Intrinsics.checkParameterIsNotNull(firstVector, "firstVector");
      //Intrinsics.checkParameterIsNotNull(secondVector, "secondVector");
      return new Vector2(firstVector.getX() + secondVector.getX(), firstVector.getY() + secondVector.getY());
    }
    

    public static final Vector2 subtract(Vector2 firstVector, Vector2 secondVector) {
      //Intrinsics.checkParameterIsNotNull(firstVector, "firstVector");
      //Intrinsics.checkParameterIsNotNull(secondVector, "secondVector");
      return new Vector2(firstVector.getX() - secondVector.getX(), firstVector.getY() - secondVector.getY());
    }
    
    
    public static final Vector2 multiply(Vector2 firstVector, double factor) {
      //Intrinsics.checkParameterIsNotNull(firstVector, "firstVector");
      return new Vector2(firstVector.getX() * factor, firstVector.getY() * factor);
    }
    
    
    public static final Vector2 divide(Vector2 firstVector, double quotient) {
      //Intrinsics.checkParameterIsNotNull(firstVector, "firstVector");
      return new Vector2(firstVector.getX() / quotient, firstVector.getY() / quotient);
    }
    
    
    public static final Vector2 perpendicular(Vector2 vector) {
      //Intrinsics.checkParameterIsNotNull(vector, "vector");
      return new Vector2(vector.getY(), -vector.getX());
    }
    
    public final double length(Vector2 vector) {
      //Intrinsics.checkParameterIsNotNull(vector, "vector");
      return Math.sqrt(dot(vector, vector));
    }
    
    public final double angle(Vector2 vector) {
      //Intrinsics.checkParameterIsNotNull(vector, "vector");
      return Math.atan2(vector.getX(), vector.getY());
    }
    
    
    public final Vector2 normalize(Vector2 vector) {
      //Intrinsics.checkParameterIsNotNull(vector, "vector");
      return divide(vector, length(vector));
    }
    
    public final double dot(Vector2 vecA, Vector2 vecB) {
      //Intrinsics.checkParameterIsNotNull(vecA, "vecA");
      //Intrinsics.checkParameterIsNotNull(vecB, "vecB");
      return vecA.getX() * vecB.getX() + vecA.getY() * vecB.getY();
    }
  }
  public final Vector2 rotateDegrees(double degrees) {
    return rotateRadians(Math.toRadians(degrees));
  }
  

  public final Vector2 unaryPlus() {
    return times(1.0D);
  }
  

  public final Vector2 unaryMinus() {
    return times(-1.0D);
  }
  

  public final Vector2 plus( Vector2 b) {
    //Intrinsics.checkParameterIsNotNull(b, "b");
    return new Vector2(this.x + b.x, this.y + b.y);
  }
  

  public final Vector2 minus(Vector2 b) {
    //Intrinsics.checkParameterIsNotNull(b, "b");
    return new Vector2(this.x - b.x, this.y - b.y);
  }
  

  public final Vector2 times(double scalar) {
    return new Vector2(this.x * scalar, this.y * scalar);
  }
  

  public final Vector2 div(double scalar) {
    return new Vector2(this.x / scalar, this.y / scalar);
  }
  
  public final double dot(Vector2 b) {
    //Intrinsics.checkParameterIsNotNull(b, "b");
    return this.x * b.x + this.y * b.y;
  }
  

  public final Vector2 normalize() {
    return div(getLength());
  }
  

  public final Vector2 perpendicular() {
    return new Vector2(this.y, -this.x);
  }
  

  public final Vector2 mirrorXAxis() {
    return new Vector2(-this.x, this.y);
  }

  public final Vector2 mirrorYAxis() {
    return new Vector2(this.x, -this.y);
  }
  
  public final double distance(Vector2 other) {
   // Intrinsics.checkParameterIsNotNull(other, "other");
    return Math.hypot(this.x - other.x, this.y - other.y);
  }
  
  public final void set(Vector2 other) {
    //Intrinsics.checkParameterIsNotNull(other, "other");
    this.x = other.x;
    this.y = other.y;
  }
  
  public final void set(double X, double Y) {
    this.x = X;
    this.y = Y;
  }
  
  public final double component1() {
    return this.x;
  }
  
  public final double component2() {
    return this.y;
  }
  

  public final Vector2 copy(double x, double y) {
    return new Vector2(x, y);
  }

  public String toString() {
    return "Vector2(x=" + this.x + ", y=" + this.y + ")";
  }
  
  public int hashCode() {
    return Double.hashCode(this.x) * 31 + Double.hashCode(this.y);
  }
  
  public boolean equals(Object paramObject) {
    if (this != paramObject) {
      if (paramObject instanceof Vector2) {
        Vector2 vector2 = (Vector2)paramObject;
        if (Double.compare(this.x, vector2.x) == 0 && Double.compare(this.y, vector2.y) == 0)
          return true; 
      } 
    } else {
      return true;
    } 
    return false;
  }
}
