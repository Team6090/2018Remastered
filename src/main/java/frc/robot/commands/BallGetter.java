/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import net.bancino.robotics.jlimelight.Limelight;
import net.bancino.robotics.jlimelight.CameraMode;
import net.bancino.robotics.jlimelight.LedMode;
import net.bancino.robotics.jlimelight.StreamMode;

public class BallGetter extends CommandBase {

    private DriveTrain drivetrain;

    Limelight limelight = new Limelight();
    LedMode initState = LedMode.FORCE_OFF; // Sets the initialization LED state
    CameraMode initCamMode = CameraMode.DRIVER; // Sets the initialization to DRIVER/VISION mode
    StreamMode streamMode = StreamMode.STANDARD; // Sets the stream mode to Main, Secondary, or Standard
    public double[] blobsReport;
    public double blobbyX;
    public double initDriveZ = .5;
    public double driveZ;
    public double[] offsetBounds = { 155, 165 };
    public double rampUpperBound = 175;
    public double rampLowerBound = 145;

    public BallGetter(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        /** This sets the LED state */
        limelight.setLedMode(initState);
        /** This sets the streaming mode */
        limelight.setStreamMode(streamMode);
        /** This sets the pipeline */
        limelight.setPipeline(3);
        /** This sets the camera mode */
        limelight.setCameraMode(initCamMode);
    }

    @Override
    public void execute() {
        limelight.setCameraMode(initCamMode);
        blobsReport = NetworkTableInstance.getDefault().getTable("GRIP").getEntry("BlobsReport/x")
                .getDoubleArray(new double[0]);
        if (blobsReport.length > 0) {
            blobbyX = blobsReport[0];
        } else {
            blobbyX = 0;
        }

        /** If target is within the set bounds... */
        if (blobbyX != 0) {
            /** If the target is between a ramp rate bound and the offset bounds... */
            if ((blobbyX < rampUpperBound && blobbyX > offsetBounds[1]) || (blobbyX > rampLowerBound && blobbyX < offsetBounds[0])) {
                driveZ = initDriveZ * .3;
                /** Else, if none of the above, keep the drive rate the same */
            } else {
                driveZ = initDriveZ;
            }
            /** Sets the motors based on what portion of the bounds the target is in */
            if (blobbyX < offsetBounds[1] && blobbyX > offsetBounds[0]) {
                drivetrain.arcadeDrive(0, 0);
            } else {
                /** If target is offset to the right */
                if (blobbyX > offsetBounds[1]) {
                    drivetrain.arcadeDrive(0, driveZ);
                    /** If target is offset to the left */
                } else if (blobbyX < offsetBounds[0]) {
                    drivetrain.arcadeDrive(0, -driveZ);
                }
            }
        } else {
            /** If nothing applies, this is really just a backup to lock the motors */
            drivetrain.arcadeDrive(0, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}