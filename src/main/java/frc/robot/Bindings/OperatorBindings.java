package frc.robot.Bindings;

import com.stzteam.mars.models.containers.Binding;
import com.stzteam.mars.operator.ControllerOI;

import frc.robot.Mechanisms.Arm;
import frc.robot.Requests.ArmRequestsFactory;

@SuppressWarnings("unused")
public class OperatorBindings implements Binding{

    private final ControllerOI operator;
    private final Arm arm;
    
    public OperatorBindings( ControllerOI operator, Arm arm){
        this.operator = operator;
        this.arm = arm;
    }

    public static OperatorBindings Create(ControllerOI operator, Arm arm){
        return new OperatorBindings(operator, arm);
    }

    @Override
    public void bind(){
        
        var buttons = operator.getActionButtons();
        var bumpers = operator.getBumpers();
        var operatorSystem = operator.getSystemTriggers();
        var leftStick = operator.getLeftStick();
        var rightStick = operator.getRightStick();
        var triggers = operator.getAnalogTriggers();
        var pov = operator.getDPadTriggers();

        pov.up().onTrue(arm.setControl(
            ()-> ArmRequestsFactory.setPosition().withTargetAngle(90).withTolerance(5)));
        pov.down().onTrue(arm.setControl(
            ()-> ArmRequestsFactory.setPosition().withTargetAngle(0).withTolerance(1)));
    }
    
}
