/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.frcteam2910.common.math.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class AutonomousCommand extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutonomousCommand() {
    requires(Robot.drivetrainSubsystem);
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    //IntakeCommand intakeCommand = new IntakeCommand();
    //addSequential(intakeCommand);
    //addSequential(Robot.drivetrainSubsystem.getDefaultCommand());

    Vector2 translation = new Vector2(0, 1000);
    DriveStraight driveStraight = new DriveStraight(translation);
    addSequential(driveStraight);

    //DriveCommand driveCommand = new DriveCommand(translation, rotation, fieldOriented);

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
