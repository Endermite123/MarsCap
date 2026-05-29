package frc.robot.Simulation;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.IOs.FlywheelsIO;

public class FlyWheelIOSim implements FlywheelsIO {

    double appliedVoltage = 0.0;
    double moi = 0.002; 
    FlywheelSim flywheelSim;
    

    public FlyWheelIOSim() {

        final DCMotor gearbox = DCMotor.getNEO(1);
        double gearRatio = 1.0;
        var flywheelPlant = LinearSystemId.createFlywheelSystem(gearbox, gearRatio, moi);
        flywheelSim = new FlywheelSim(flywheelPlant, gearbox,gearRatio);

    }

    @Override
    public void updateInputs(FlywheelsInputs inputs) {
        double appliedVoltage = MathUtil.clamp(flywheelSim.getInputVoltage(), -12.0, 12.0);
        flywheelSim.update(0.02);
        inputs.voltage = appliedVoltage;
        flywheelSim.setInputVoltage(this.appliedVoltage);
        inputs.currentRPM = flywheelSim.getAngularVelocityRPM();
    }

    @Override 
    public void setVoltage(double voltage) {
        this.appliedVoltage = voltage;
    }

    @Override
    public void stop() {
        this.appliedVoltage = 0.0;
    }

    @Override
    public void setRPM(double rpm) {
        flywheelSim.setInputVoltage(this.appliedVoltage);
    }
    
}
