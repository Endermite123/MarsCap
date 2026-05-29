package frc.robot.Simulation;


import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class ElevatorCanvas {

    Mechanism2d elevatorCanvas = new Mechanism2d(2, 3);
    MechanismRoot2d elevatorRoot = elevatorCanvas.getRoot("Elevator Root", 1, 0);
    MechanismLigament2d mast = elevatorRoot.append(new MechanismLigament2d("Mast", 2.5, 90, 10, new Color8Bit(Color.kBlue)));

    public ElevatorCanvas() {
        SmartDashboard.putData("Elevator Canvas", elevatorCanvas);
    }
    
}
