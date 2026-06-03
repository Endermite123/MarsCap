package frc.tests;

import com.stzteam.mars.test.MARSTest;
import com.stzteam.mars.test.TestRoutine;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Mechanisms.Arm;
import frc.robot.Requests.ArmRequestsFactory;

@MARSTest(name = "Arm PID test")
public class ArmTest extends TestRoutine {
    private final Arm a;

    public ArmTest (Arm arm){
        this.a = arm;
    }
    
    @Override 
    public Command getRoutineCommand(){
        return Commands.sequence(
            run(ArmRequestsFactory.setPosition().withTargetAngle(90).withTolerance(5),a),
            waitFor(()->a.isAtTarget(5, a), 2),
            delay(2),
            run(ArmRequestsFactory.setPosition().withTargetAngle(0).withTolerance(5),a),
            waitFor(()->a.isAtTarget(5, a), 2),
            run(ArmRequestsFactory.idle(),a)
        );
    }
}
