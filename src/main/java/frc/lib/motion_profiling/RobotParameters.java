
package frc.lib.motion_profiling;

import com.squareup.moshi.Json;

public class RobotParameters {

@Json(name = "robotLength")
private Double robotLength;
@Json(name = "robotWidth")
private Double robotWidth;

public Double getRobotLength() {
return robotLength;
}

public void setRobotLength(Double robotLength) {
this.robotLength = robotLength;
}

public Double getRobotWidth() {
return robotWidth;
}

public void setRobotWidth(Double robotWidth) {
this.robotWidth = robotWidth;
}

}