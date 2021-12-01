package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SuperPato implements Mechanism {
    
    private DcMotor superPato;
    private double motorSpPower = 1.0;

    public void initializeHardware(HardwareMap hardwareMap){
        superPato = hardwareMap.get(DcMotor.class, "motorSuperPato");
    }

    public void dropSuperPato(){
        intake.setPower(motorSpPower);
    }

    public void stopSuperPato(){
        intake.setPower(0);
    }
}