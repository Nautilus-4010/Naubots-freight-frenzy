package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class InTake implements Mechanism {
    
    private DcMotor intake;

    public void initializeHardware(HardwareMap hardwareMap){
        intake = hardwareMap.get(DcMotor.class, "motorIntake");
    }

    public void pickFreight(){
        double motorItPower = 0.8; // Por optización
        if (gamepad1.right_trigger > 0.5) { 
            intake.setPower(motorItPower);
        }else{
            intake.setPower(0);
        }
    }

    public void dropFreight(){
        double motorItPower = 0.8; // Por optización
        if(gamepad1.left_trigger > 0.5){
            intake.setPower(-motorItPower);
        }else{
            intake.setPower(0);
        }
    }
}
