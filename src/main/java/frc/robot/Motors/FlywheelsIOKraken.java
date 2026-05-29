package frc.robot.Motors;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.IOs.FlywheelsIO;

public class FlywheelsIOKraken implements FlywheelsIO {

    private TalonFX flywheelsMotor;
    private TalonFXConfiguration flywheelsConfig;
    private TalonFXConfigurator fconfigurator;
    
    private final VelocityVoltage velocityControl = new VelocityVoltage(0);   
    private double targetRPM = 0;

    public FlywheelsIOKraken (){
        flywheelsMotor = new TalonFX(0);
        flywheelsConfig = new TalonFXConfiguration();
        fconfigurator = flywheelsMotor.getConfigurator();
        flywheelsConfig.Feedback.SensorToMechanismRatio = 1;
        fconfigurator.apply(flywheelsConfig);

    }

    @Override
    public void updateInputs(FlywheelsInputs inputs) {
        inputs.voltage = flywheelsMotor.getSupplyVoltage().getValueAsDouble();
        inputs.temperature = flywheelsMotor.getDeviceTemp().getValueAsDouble();
        inputs.current = flywheelsMotor.getSupplyCurrent().getValueAsDouble();
        inputs.currentRPM = flywheelsMotor.getVelocity().getValueAsDouble() * 60.0;
        inputs.targetRPM = this.targetRPM;

    }

    @Override
    public void setVoltage(double voltage) {
        flywheelsMotor.setVoltage(voltage);
    }

    @Override
    public void setRPM(double rpm) {
        double rps = rpm / 60.0;
        flywheelsMotor.setControl(velocityControl.withVelocity(rps));
        this.targetRPM = rpm;
    }

    @Override
    public void stop() {
        flywheelsMotor.setVoltage(0);
    }

    
}
