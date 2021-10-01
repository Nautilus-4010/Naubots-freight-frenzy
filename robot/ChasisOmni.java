package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ChasisOmni extends Mechanism{

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    
    public ChasisOmni(){}
    
    public void initializeHardware(HardwareMap hardwareMap){
        frontLeft = hardwareMap.get(DcMotor.class, "motor 1");
    }
    
    public void move(double drive, double lateral, double turn){
        double frontLeftPower = drive + lateral + turn;
        frontLeft.setPower(frontLeftPower);
    }
    
    
}