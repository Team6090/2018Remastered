/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends SubsystemBase {

  public static final int LEFT_MOTOR = 1;
  public static final int LEFT_SLAVE_MOTOR = 3;
  public static final int RIGHT_MOTOR = 2;
  public static final int RIGHT_SLAVE_MOTOR = 4;

  /**
   * The procession of motor declarations and groupings leading to differential drive.
   */

  //WPI_TalonSRX leftMotor2 = new WPI_TWPI_TalonSRX
  WPI_TalonSRX leftMotor = new WPI_TalonSRX(LEFT_MOTOR);
  WPI_TalonSRX leftSlaveMotor = new WPI_TalonSRX(LEFT_SLAVE_MOTOR);
  WPI_TalonSRX rightMotor = new WPI_TalonSRX(RIGHT_MOTOR);
  WPI_TalonSRX rightSlaveMotor = new WPI_TalonSRX(RIGHT_SLAVE_MOTOR);

  SpeedControllerGroup leftSideDrive = new SpeedControllerGroup(leftMotor, leftSlaveMotor);
  SpeedControllerGroup rightSideDrive = new SpeedControllerGroup(rightMotor, rightSlaveMotor);
  //rightSideDrive = -rightSideDrive;

  DifferentialDrive diffDrive = new DifferentialDrive(leftSideDrive, rightSideDrive);

  /**
   * Used for driving with the joystick
   * @param y The Y value from the joystick.
   * @param z The Z value from the joystick.
   */
  public void arcadeDrive(double y, double z) {
    diffDrive.arcadeDrive(y, z);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
