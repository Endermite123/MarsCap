package frc.robot.Motors;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;


import frc.robot.Constants.ArmConstants;

import frc.robot.IOs.ArmIO;

public class ArmIOKraken implements ArmIO {

    private TalonFX armMotor;
    private TalonFXConfiguration armConfig;
    private TalonFXConfigurator armConfigurator;
    private Rotation2d targetAngle = new Rotation2d();
    VoltageOut voltageRequest = new VoltageOut(0);
    MotionMagicVoltage positionRequest = new MotionMagicVoltage(0);

    
    public ArmIOKraken(){

        armMotor = new TalonFX(ArmConstants.armId);
        armConfig = new TalonFXConfiguration();
        armConfigurator = armMotor.getConfigurator();
        armConfig.Feedback.SensorToMechanismRatio = ArmConstants.gearRatio;
        armConfig.Slot0.kP = ArmConstants.kP;
        armConfig.Slot0.kI = ArmConstants.kI;
        armConfig.Slot0.kD = ArmConstants.kD;
        armConfig.Slot0.kS = ArmConstants.kS;
        armConfig.Slot0.kV = ArmConstants.kV;
        armConfig.Slot0.kA = ArmConstants.kA;

        armConfig.CurrentLimits.SupplyCurrentLimit = ArmConstants.kCurrentLimit;
        armConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        armConfig.CurrentLimits.StatorCurrentLimit = ArmConstants.kStatorCurrentLimit;
        armConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        
        armConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        armConfig.SoftwareLimitSwitch.ForwardSoftLimitThreshold = Units.degreesToRotations(ArmConstants.kUpperLimit);
        armConfig.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        armConfig.SoftwareLimitSwitch.ReverseSoftLimitThreshold = Units.degreesToRotations(ArmConstants.kLowerLimit);
        armConfig.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;

        armConfigurator.apply(armConfig);
    }

    @Override
    public void updateInputs (ArmInputs inputs){
        inputs.current = armMotor.getStatorCurrent().getValueAsDouble();
        inputs.voltage = armMotor.getMotorVoltage().getValueAsDouble();
        inputs.currentAngle = new Rotation2d(armMotor.getPosition().getValueAsDouble());
        inputs.targetAngle = this.targetAngle;
    }
    
    @Override
    public void setVoltage(double voltage){
        armMotor.setControl(voltageRequest.withOutput(voltage));
    }

    @Override
    public void setPosition(double angle){
        armMotor.setControl(positionRequest.withSlot(0).withPosition(angle));
    }

    @Override
    public void resetEncoder(){
        armMotor.setPosition(0);
    }

    @Override 
    public void stop(){
        armMotor.stopMotor();
    }

}
