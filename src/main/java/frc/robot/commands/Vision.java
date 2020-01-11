/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import net.bancino.robotics.jlimelight.Limelight;
import net.bancino.robotics.jlimelight.Limelight.CameraMode;
import net.bancino.robotics.jlimelight.Limelight.LedState;
import net.bancino.robotics.jlimelight.Limelight.StreamMode;

public class Vision extends CommandBase {

    Limelight limelight = new Limelight(); // Declare the limelight as, indeed, a limelight
    LedState initState = LedState.FORCE_ON; // Sets the initialization LED state
    CameraMode initCamMode = CameraMode.VISION; // Sets the initialization to DRIVER/VISION mode
    int pipeline = 0; // Sets the pipeline
    StreamMode streamMode = StreamMode.STANDARD; // Sets the stream mode to Main, Secondary, or Standard

    public Vision(Limelight limelight) {
        this.limelight = limelight;
        addRequirements();
    }

    // Called when the command is initially scheduled.
     @Override
     public void initialize() {
         /** This sets the LED state */
         limelight.setLedMode(initState);
         /** This sets the camera mode */
         limelight.setCameraMode(initCamMode);
         /** This sets the streaming mode */
         limelight.setStreamingMode(streamMode);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        /** All of this next block is just going to output information to the SmartDashboard */
        SmartDashboard.putBoolean("HasTarget: ", limelight.hasValidTargets());
        SmartDashboard.putNumber("Area", limelight.getTargetArea());
        SmartDashboard.putNumber("Horizontal Offset: ", limelight.getHorizontalOffset());
        SmartDashboard.putNumber("Vertical Offset: ", limelight.getVerticalOffset());
        SmartDashboard.putNumber("Skew: ", limelight.getSkew());
        SmartDashboard.putNumber("CamTran: ", limelight.getCamTran()); // Translation (x,y,y) Rotation(pitch,yaw,roll)
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