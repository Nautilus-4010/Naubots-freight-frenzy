package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class InTake implements Mechanism {
    
    private DcMotor intake;
    private double motorItPower = 1.0;

    public void initializeHardware(HardwareMap hardwareMap){
        intake = hardwareMap.get(DcMotor.class, "motorIntake");
    }

    public void pickFreight(){
        intake.setPower(motorItPower);
    }

    public void dropFreight(){
        intake.setPower(-motorItPower);
    }

    public void stopInTake(){
        intake.setPower(0);
    }
}
