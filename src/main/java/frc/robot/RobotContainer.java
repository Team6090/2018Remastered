/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BallGetter;
import frc.robot.commands.ColorSensor;
import frc.robot.commands.PathCorrection;
import frc.robot.commands.Vision;
import frc.robot.subsystems.DriveTrain;
import net.bancino.robotics.jlimelight.Limelight;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Define robot's subsystem and commands here...

  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private DriveTrain drivetrain = new DriveTrain();

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static Joystick joystick = new Joystick(0);

  private static Limelight limelight = new Limelight();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton visionButton = new JoystickButton(joystick, 3);
    visionButton.whenPressed(new Vision(limelight));
    JoystickButton pathFinderButton = new JoystickButton(joystick, 4);
    pathFinderButton.whenPressed(new PathCorrection());
    JoystickButton colorSensorButton = new JoystickButton(joystick, 5);
    colorSensorButton.whenPressed(new ColorSensor());
    JoystickButton ballGetter = new JoystickButton(joystick, 7);
    ballGetter.toggleWhenPressed(new BallGetter(drivetrain));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // This command will run in autonomous
    return null;
  }
}
