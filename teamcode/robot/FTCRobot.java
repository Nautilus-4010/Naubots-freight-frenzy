package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class FTCRobot {
    
    protected OpMode programa;

    public ChasisOmni chasis;
    public RobotVision vision;
    
    public FTCRobot(OpMode programa){
        this.programa = programa;
        this.chasis = new ChasisOmni();
    }
    
    public void initializeMechanisms(){
        HardwareMap hwMap = programa.hardwareMap;
        //chasis.initializeHardware(hwMap);
        vision.initializeHardware(hwMap);
        programa.telemetry.addData("Status", "Ready to rumbleee!!!");
    }

    public void logMechanismStatus(){
        String identifiedTarget = vision.getTargetName();
        programa.telemetry.addData("Identified target:", identifiedTarget);
    }
}