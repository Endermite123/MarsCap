package frc.robot.IOs;

import com.stzteam.features.marsprocessor.Fallback;
import com.stzteam.mars.models.singlemodule.Data;
import com.stzteam.mars.models.singlemodule.IO;

import edu.wpi.first.math.geometry.Rotation2d;

@Fallback
public interface ArmIO extends IO<ArmIO.ArmInputs> {
    public static class ArmInputs extends Data<ArmInputs> {
        public Rotation2d currentAngle = new Rotation2d();
        public Rotation2d targetAngle = new Rotation2d();;
        public double voltage = 0;
        public double current = 0;

        @Override
        public ArmInputs snapshot() {
        ArmInputs clone = new ArmInputs();
        
        clone.timestamp = this.timestamp;
        clone.key = this.key;
        clone.currentAngle = this.currentAngle;
        clone.targetAngle = this.targetAngle;
        clone.voltage = this.voltage;
        clone.current = this.current;
        return clone;
}
    }

    public void setVoltage(double voltage);
    public void setPosition(double angle);
    public void resetEncoder();
    public void stop();



    
}
