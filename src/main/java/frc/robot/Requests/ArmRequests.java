package frc.robot.Requests;

import com.stzteam.features.marsprocessor.CreateCommand;
import com.stzteam.features.marsprocessor.RequestFactory;
import com.stzteam.mars.diagnostics.ActionStatus;
import com.stzteam.mars.diagnostics.ModuleColorCode;
import com.stzteam.mars.diagnostics.StatusColorCode.Severity;
import com.stzteam.mars.requests.Request;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.IOs.ArmIO;
import frc.robot.IOs.ArmIO.ArmInputs;

@RequestFactory
public interface ArmRequests extends Request<ArmInputs, ArmIO> {

    public static final ModuleColorCode IDLE = ModuleColorCode.solid("Idle", Severity.OK, Color.kGreen, "Brazo en reposo");
    public static final ModuleColorCode MANUAL_MOVEMENT = ModuleColorCode.solid("Manual control", Severity.WARNING, Color.kTeal, "Operador moviendo");
    public static final ModuleColorCode MOVING = ModuleColorCode.solid("Reaching target", Severity.WARNING, Color.kLightBlue, "Llendo al setpoint");
    public static final ModuleColorCode ATPOINT = ModuleColorCode.solid("Reaching target", Severity.OK, Color.kGreen, "En el setpoint");


    @CreateCommand (name = "stop")
    public static class Idle implements ArmRequests {
        @Override
        public ActionStatus apply(ArmInputs data, ArmIO actor){
            actor.stop();
            return ActionStatus.of(IDLE, "Idle");
        }
    }
    
    @CreateCommand (name = "move")
    public static class Move implements ArmRequests{

        private double voltage;
        public Move withVoltage (double voltage){
            this.voltage = voltage;
            return this;
        }

        @Override 
        public ActionStatus apply(ArmInputs data, ArmIO actor){
            if (data.currentAngle.getDegrees()<180 && data.currentAngle.getDegrees()>0 ){
                actor.setVoltage(voltage);
            } else {
                actor.stop();
            }
        return ActionStatus.of (MANUAL_MOVEMENT, "Moving");
        }
    }

    @CreateCommand (name = "set_position")
    public static class SetPosition implements ArmRequests{
        private double angle;
        private double toleranceDegrees;

        public SetPosition withTargetAngle (double angle){
            this.angle = angle;
            return this;
        }
        
        public SetPosition withTolerance ( double degrees){
            this.toleranceDegrees = degrees;
            return this;
        }

        @Override
        public ActionStatus apply (ArmInputs data, ArmIO actor){
            actor.setPosition(angle);

            boolean isAtSetpoint = 
                MathUtil.isNear(angle, data.currentAngle.getDegrees(), toleranceDegrees);

            if (isAtSetpoint) {
            return ActionStatus.of( ATPOINT, "Setpoint reached");
            } else{
            return ActionStatus.of(MOVING, "Setpoint not reached yet");
            }
        }

    }


}
