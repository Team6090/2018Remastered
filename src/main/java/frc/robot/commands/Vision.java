/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import net.bancino.robotics.jlimelight.Limelight;
import net.bancino.robotics.jlimelight.Limelight.CameraMode;
import net.bancino.robotics.jlimelight.Limelight.LedState;
import net.bancino.robotics.jlimelight.Limelight.StreamMode;

public class Vision extends CommandBase {
    
    Limelight limelight = new Limelight();
    LedState initState = LedState.FORCE_ON; // Sets the initialization LED state
    CameraMode initCamMode = CameraMode.VISION; // Sets the initialization to DRIVER/VISION mode
    StreamMode streamMode = StreamMode.STANDARD; // Sets the stream mode to Main, Secondary, or Standard
    Boolean povCamMode = true;
    Boolean ledOn = true;
    int pipeline = 0; // Sets the pipeline

    /*
     *  Finds the distance from the Limelight to the target.
     *  Formula: (h2-h1)/tan(a2-a1)=d
     * 
     *  @param collinTx Horizontal Offset
     *  @param collinTy Vertical Offset
     *  @param collinA1 Angle of the mounted Limelight
     *  @param collinH1 Height of the Limelight
     *  @param collinH2 Height of the target
     *  @param collinD Distance to the target
     */
    public double DistanceToTarget() {
        //double collinTx = limelight.getHorizontalOffset();
        double collinTy = limelight.getVerticalOffset();
        double collinTyRadians = collinTy*(Math.PI/180);
        double collinA1 = 0;
        double collinH1 = 43.5;
        double collinH2 = 81;
        double collinD;

        collinD = (collinH2-collinH1)/(Math.tan(collinTyRadians-collinA1));
        
        return collinD;
    }

    public Vision() {
        //addRequirements();
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
        /** The logic to switch between vision and driver */
        switch (RobotContainer.joystick.getPOV()) {
           case 0:
            if (povCamMode) {
                limelight.setCameraMode(CameraMode.VISION);
            } else {
                limelight.setCameraMode(CameraMode.DRIVER);
            }
            povCamMode = !povCamMode;
        }

        /** The logic to switch LED modes */
        switch (RobotContainer.joystick.getPOV()) {
            case 180:
                if (ledOn) {
                    limelight.setLedMode(LedState.FORCE_ON);
                } else {
                    limelight.setLedMode(LedState.FORCE_OFF);
                }
                ledOn = !ledOn;
        }

        /** All of this next block is just going to output information to the SmartDashboard */
        SmartDashboard.putBoolean("Limelight/HasTarget: ", limelight.hasValidTargets());
        SmartDashboard.putNumber("Limelight/Area", limelight.getTargetArea());
        SmartDashboard.putNumber("Limelight/Horizontal Offset: ", limelight.getHorizontalOffset());
        SmartDashboard.putNumber("Limelight/Vertical Offset: ", limelight.getVerticalOffset());
        SmartDashboard.putNumber("Limelight/Skew: ", limelight.getSkew());
        SmartDashboard.putNumberArray("Limelight/CamTran: ", limelight.getCamTran()); // Translation (x,y,y) Rotation(pitch,yaw,roll)
        SmartDashboard.putNumber("Limelight/CollinD: ", DistanceToTarget());
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