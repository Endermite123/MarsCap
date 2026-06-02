package frc.robot.Constants;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Mass;

import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;

public class ArmConstants {

    public ArmConstants(){}

    public static final int armId = 1;
    public static final int gearRatio = 30;

    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kS = 0;
    public static final double kV = 0;
    public static final double kA = 0;

    public static final int kCurrentLimit = 40;
    public static final double kMaxVolts = 12;
    public static final double kStatorCurrentLimit = 40;
    public static final double kUpperLimit = 180;
    public static final double kLowerLimit = 0;

    // Simulation

    public static final Distance kLength = Meters.of(0.15);
    public static final Mass kMass = Kilograms.of(5);
    public static final boolean gravity = true;




    
}
