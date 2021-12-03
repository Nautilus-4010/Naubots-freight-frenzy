package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class Naubot {
    
    private double INTAKE_POWER = 1.0;
    private double SUPER_PATO_POWER = 1.0;
    
    private OpMode programa;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    
    public DcMotor cuatroBarras;
    private DcMotor intake;
    private DcMotor superPato;
    
    public Naubot(OpMode programa){
        this.programa = programa;
    }
    
    public void initializeHardware(){
        HardwareMap hardwareMap = programa.hardwareMap;
        frontLeft = hardwareMap.get(DcMotor.class, "motorFl");
        frontRight = hardwareMap.get(DcMotor.class, "motorFr");
        backLeft = hardwareMap.get(DcMotor.class, "motorBl");
        backRight = hardwareMap.get(DcMotor.class, "motorBr");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        
        cuatroBarras = hardwareMap.get(DcMotor.class, "motor4b");
        intake = hardwareMap.get(DcMotor.class, "motorIntake");
        superPato = hardwareMap.get(DcMotor.class, "motorSuperPato");
    }
    
    public void move(double drive, double lateral, double turn){
        double frontLeftPower = drive + lateral + turn;
        double frontRightPower = drive - lateral - turn;
        double backLeftPower = drive - lateral + turn;
        double backRightPower = drive + lateral - turn;
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
    
    public void pickFreight(){
        intake.setPower(INTAKE_POWER);
    }

    public void dropFreight(){
        intake.setPower(-INTAKE_POWER);
    }

    public void stopInTake(){
        intake.setPower(0);
    }
    
    public void dropSuperPato(){
        superPato.setPower(SUPER_PATO_POWER);
    }

    public void stopSuperPato(){
        superPato.setPower(0);
    }
}
