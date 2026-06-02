package frc.robot.Simulation;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.Constants.ArmConstants;
import frc.robot.IOs.ArmIO;
import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;

public class ArmCanvas implements ArmIO {

    final SingleJointedArmSim armSim;
    final ProfiledPIDController armController;
    double appliedVoltage = 0;
    boolean isClosedLoop = false;
    double currentTargetAngle =0;

    public ArmCanvas(){
        armSim = new SingleJointedArmSim(
            DCMotor.getKrakenX60(1),
            ArmConstants.gearRatio, 
            SingleJointedArmSim.estimateMOI(
                ArmConstants.kLength.in(Meters),
                ArmConstants.kMass.in(Kilograms)
            ), 
            ArmConstants.kLength.in(Meters), 
            Units.degreesToRadians(ArmConstants.kLowerLimit), 
            Units.degreesToRadians(ArmConstants.kUpperLimit), 
            ArmConstants.gravity,
            Units.degreesToRadians(0)
        );
        armController = new ProfiledPIDController(0, 0, 0, 
            new TrapezoidProfile.Constraints(2,4)); 
    }

    @Override
    public void updateInputs (ArmInputs inputs){
        if (isClosedLoop){
            appliedVoltage = armController.calculate(Units.radiansToRotations(armSim.getAngleRads()), currentTargetAngle);
        }
        appliedVoltage = MathUtil.clamp(appliedVoltage,-12,12);
        armSim.setInputVoltage(appliedVoltage);
        armSim.update(0.02);
        inputs.currentAngle = Rotation2d.fromRadians(armSim.getAngleRads());
        inputs.targetAngle = Rotation2d.fromRotations(currentTargetAngle);
        inputs.voltage = appliedVoltage;
        inputs.current = armSim.getCurrentDrawAmps();
    }

    @Override
    public void setVoltage(double voltage){
        isClosedLoop = false;
        appliedVoltage = voltage;
    }

    @Override
    public void setPosition(double angle){
        isClosedLoop = true;
        Rotation2d a = new Rotation2d(angle);
        this.currentTargetAngle = a.getRotations();
    }
    
    @Override
    public void resetEncoder(){
    }

    @Override
    public void stop(){
        this.appliedVoltage = 0;
    }
    
}
