  
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;



public class ColorWheelRotation extends CommandBase {

    public Color currentColor, previousColor;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    public int revCount;
    public boolean rotationComplete;
    edu.wpi.first.wpilibj.PWMVictorSPX pwm = new edu.wpi.first.wpilibj.PWMVictorSPX(0);

    public ColorWheelRotation() {
    }
        public void initialize(){
            revCount = 0;
            rotationComplete = false;
        }

        public void execute(){
            currentColor = colorSensor.getColor();
            if (previousColor != currentColor){
                revCount++;
                previousColor = colorSensor.getColor();
                previousColor = currentColor;
            }
            if (revCount >= 25) {
                /* Reset everything to the starting configuration for the next run. */
                pwm.setSpeed(0.0d);
                revCount = 0;
                previousColor = null;
                rotationComplete = true;
            } else {
                pwm.setSpeed(0.25d);
            }
        }
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return rotationComplete;
    }
}

