package frc.robot.Mechanisms;

import java.util.function.Supplier;

import com.stzteam.forgemini.io.NetworkIO;
import com.stzteam.mars.models.SubsystemBuilder;
import com.stzteam.mars.models.Telemetry;
import com.stzteam.mars.models.singlemodule.ModularSubsystem;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.IOs.ArmIO;
import frc.robot.IOs.ArmIO.ArmInputs;
import frc.robot.Requests.ArmRequests;
import frc.robot.Requests.ArmRequestsCommands;
import frc.robot.configuration.KeyManager;

public class Arm extends ModularSubsystem<ArmInputs, ArmIO> implements ArmRequestsCommands {

    public Arm (ArmIO io){
        super(
            SubsystemBuilder.<ArmInputs,ArmIO> setup()
                .key(KeyManager.ARM_KEY)
                .hardware(io, new ArmInputs())
                .request(new ArmRequests.Idle())
                .telemetry(new ArmTelemetry())
        );
        setDefaultCommand(runRequest(()-> new ArmRequests.Idle()));
    }

    @Override
    public ArmInputs getState(){
        return inputs;
    }

    @Override
    public Command setControl(Supplier<ArmRequests> request){
        return runRequest(request);
    }

    @Override
    public void absolutePeriodic(ArmInputs data){}

    @Override 
    public void simulationPeriodic(){}

    public static class ArmTelemetry extends Telemetry<ArmInputs>{

        @Override
        public void telemeterize(ArmInputs data) {
            NetworkIO.set(KeyManager.ARM_KEY, "Position", data.currentAngle);
        }
    }

        public boolean isAtTarget(double toleranceDegrees, Arm arm){
        boolean isAtTarget = 
        MathUtil.isNear(arm.getState().targetAngle.getDegrees(), arm.getState().currentAngle.getDegrees(), toleranceDegrees);
        return isAtTarget;
    }

    
} 
