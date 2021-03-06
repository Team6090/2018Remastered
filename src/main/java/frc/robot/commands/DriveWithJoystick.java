/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystick extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private Joystick joystick;
  private DriveTrain drivetrain;

  /**
   * Creates a new command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveWithJoystick(Joystick joystick, DriveTrain drivetrain) {
    this.joystick = joystick;
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double throttle = joystick.getRawAxis(3);
    /* Converts throttle to a 0-1 scale */
    throttle = (throttle + 1) / 2;
    /* Fetch Y and Z values from joystick */
    double joysticky = joystick.getY();
    double joystickz = joystick.getZ();
    /* Multiply the y and z values by the throttle */
    double throttledjoysticky = joysticky * throttle;
    double throttledjoystickz = -joystickz * throttle;
    /* Put the throttled Y and Z values into the arcade drive */
    drivetrain.arcadeDrive(throttledjoysticky, throttledjoystickz);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
