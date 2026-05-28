package frc.robot.IOs;

import com.stzteam.mars.models.singlemodule.Data;
import com.stzteam.mars.models.singlemodule.IO;

public interface FlywheelsIO extends IO<FlywheelsIO.FlywheelsInputs> {
    public static class FlywheelsInputs extends Data<FlywheelsInputs> {
        public double currentRPM = 0.0;
        public double targetRPM = 0.0;
        public double temperature = 0;
        public double voltage = 0;
        public double current = 0;
        public boolean discSensor = false;
    }

    public void setVoltage(double voltage);
    public void setRPM(double rpm);
    public void reverse(double speed);
    public void stop();

    
}
