package frc.robot.Simulation;


import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class ArmCanvas {

    Mechanism2d armCanvas = new Mechanism2d(3,3);
    MechanismRoot2d armRoot = armCanvas.getRoot("Arm Root",1.5, 1.5);
    MechanismLigament2d bicep = armRoot.append(new MechanismLigament2d("Bicep", 0.8, 45, 12, new Color8Bit(Color.kRed)));
    MechanismLigament2d forearm = bicep.append(new MechanismLigament2d("Forearm", 0.6, -20, 8, new Color8Bit(Color.kYellow)));


    public ArmCanvas() {
        SmartDashboard.putData("Arm Canvas", armCanvas);
    }
    
}
