package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterHood extends CommandBase{
    private final WPI_TalonSRX hoodMotor = new WPI_TalonSRX(5);

public ShooterHood(){

    }
public void execute(){
    
}
}