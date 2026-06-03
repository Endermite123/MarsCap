package frc.robot.Bindings;

import com.stzteam.mars.models.containers.Binding;
import com.stzteam.mars.test.TestRoutine;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Mechanisms.Arm;
import frc.tests.ArmTest;

public class TestBindings implements Binding {

    private SendableChooser<TestRoutine> tests = new SendableChooser<>();
    private Arm arm;
    
    public TestBindings(Arm arm){
        this.arm = arm;
    }

    public static TestBindings create (Arm arm){
        return new TestBindings(arm);
    }

    @Override 
    public void bind (){
        tests.addOption("ArmTest", new ArmTest(arm));

        SmartDashboard.putData("TestRoutines", tests);
    }

    public TestRoutine getSelected (){
        return tests.getSelected();
    }
    
}
