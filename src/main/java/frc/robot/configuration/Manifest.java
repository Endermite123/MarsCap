package frc.robot.configuration;

import com.stzteam.mars.builder.Environment;
import com.stzteam.mars.builder.Environment.RunMode;
import com.stzteam.mars.builder.Injector;
import com.stzteam.mars.operator.ControllerOI;
import com.stzteam.mars.operator.PS5OI;
import com.stzteam.mars.operator.XboxOI;

import frc.robot.IOs.ArmIO;
import frc.robot.IOs.ArmIOFallback;
import frc.robot.Mechanisms.Arm;
import frc.robot.Motors.ArmIOKraken;
import frc.robot.Simulation.ArmCanvas;

public class Manifest {

    public static final boolean HAS_ARM = true;

    public enum ControllerType{PS5,XBOX}
    public static final int driver_port =0;
    public static final int operator_port =1;

    public static final ControllerType driver = ControllerType.XBOX;
    public static final ControllerType operator = ControllerType.XBOX;

    public static class ControlsBuilder{
        public static ControllerOI buildDriver(){
            return driver == ControllerType.PS5
            ? new PS5OI(driver_port)
            : new XboxOI(driver_port);
        }
        public static ControllerOI buildOperator(){
            return operator == ControllerType.PS5
            ? new PS5OI(operator_port)
            : new XboxOI(operator_port);
        }
    }
    public static final RunMode CURRENT_MODE = RunMode.SIM;

    static{Environment.setMode(CURRENT_MODE);}

    public static Arm buildArm(){
        ArmIO io = Injector.createIO(HAS_ARM, ArmIOFallback::new, ArmIOKraken::new, ArmCanvas::new);
        return new Arm(io);
    } 
}
