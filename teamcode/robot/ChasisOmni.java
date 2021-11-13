package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ChasisOmni implements Mechanism{

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    
    public ChasisOmni(){}
    
    public void initializeHardware(HardwareMap hardwareMap){
        frontLeft = hardwareMap.get(DcMotor.class, "motorFl");
        frontRight = hardwareMap.get(DcMotor.class, "motorFr");
        backLeft = hardwareMap.get(DcMotor.class, "motorBl");
        backRight = hardwareMap.get(DcMotor.class, "motorBr");
        //backLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    
    public void move(double drive, double lateral, double turn){
        double frontLeftPower = drive + lateral + turn;
        double frontRightPower = drive - lateral - turn;
        double backLeftPower = drive - lateral + turn;
        double backRightPower = drive + lateral - turn;
        // TODO: Assign the corresponding power to each motor
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
    
    
}