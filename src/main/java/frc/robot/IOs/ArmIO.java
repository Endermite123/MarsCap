package frc.robot.IOs;

import com.stzteam.mars.models.singlemodule.Data;
import com.stzteam.mars.models.singlemodule.IO;

public interface ArmIO extends IO<ArmIO.ArmInputs> {
    public static class ArmInputs extends Data<ArmInputs> {
        public double currentAngle = 0.0;
        public double targetAngle = 0.0;
        public double rps = 0;
        public double voltage = 0;
        public double current = 0;
        public boolean lowerSensor = false;
        public boolean upperSensor = false;
    }

    public void setVoltage(double voltage);
    public void setDutyCycle(double speed);
    public void setPosition(double angle);
    public void setAbsolute0();
    public void stop();

        //Setters
        public void setCurrentAngle(double angle);
        public void setTargetAngle(double angle);
    
        //Getters
        public double getCurrentAngle();
        public double getTargetAngle();

    
}
