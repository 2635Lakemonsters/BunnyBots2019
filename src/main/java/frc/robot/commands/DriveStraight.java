package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import java.util.ArrayList;

import org.frcteam2910.common.math.Vector2;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class DriveStraight extends Command {
  Vector2 translation;
  
    public DriveStraight(Vector2 translation) {
        super(3000.0);
        requires(Robot.drivetrainSubsystem);
        this.translation = translation;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrainSubsystem.getGyroscope().setAdjustmentAngle(Robot.drivetrainSubsystem.getGyroscope().getUnadjustedAngle());
        Vector2 position = new Vector2(0, 0);
        Robot.drivetrainSubsystem.resetKinematics(position, 0);
    	//Robot.drive.reset();
      	//motionParams = MotionMagicLibrary.getDriveParameters(RobotMap.WHEEL_RADIUS_INCHES, distance, velocity, false, acceleration);
    	//Robot.drivetrainSubsystem.motionDriveInit(motionParams);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrainSubsystem.holonomicDrive(translation, 0.0, true);
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    @Override protected boolean isFinished() {
    	
    	boolean isFinished = isTimedOut();
    	if (!isFinished)
        isFinished = Robot.drivetrainSubsystem.autonomousDriveFinished(translation);      
        
    	if(isFinished) {
//    		double currentHeading = Robot.drive.getNavxHeading();
//    		double navxHeading = Math.abs(intialHeading-currentHeading);
//    		System.out.println("Navx straight heading:" + navxHeading);
//    		System.out.println("Navx straight-drive angle: " + Robot.drive.getNavxAngle());
    		System.out.println("Drive Straight Finished.");
    		System.out.println("-----------");
    	}
    	
    	return isFinished;
    }
}