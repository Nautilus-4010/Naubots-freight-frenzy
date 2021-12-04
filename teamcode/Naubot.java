package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class Naubot {
    
    private double INTAKE_POWER = 1.0;
    private double SUPER_PATO_POWER = 1.0;
    private static final double TICKS_PER_CM = 27.526;
    
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
        resetEncoders();
        
        cuatroBarras = hardwareMap.get(DcMotor.class, "motor4b");
        intake = hardwareMap.get(DcMotor.class, "motorIntake");
        superPato = hardwareMap.get(DcMotor.class, "motorSuperPato");
    }
    
    public void moverLateral(double distancia){
        int target = (int) Math.round(distancia * TICKS_PER_CM);
        resetEncoders();
        frontLeft.setTargetPosition(target);
        frontRight.setTargetPosition(-target);
        backLeft.setTargetPosition(-target);
        backRight.setTargetPosition(target);
        initAutoDrive();
    }
    
    public void avanzar(double distancia){
        int target = (int) Math.round(distancia * TICKS_PER_CM);
        resetEncoders();
        frontLeft.setTargetPosition(target);
        frontRight.setTargetPosition(target);
        backLeft.setTargetPosition(target);
        backRight.setTargetPosition(target);
        initAutoDrive();
    }
    
    public void move(double drive, double lateral, double turn, double multiplier){
        double frontLeftPower = drive + lateral + turn;
        double frontRightPower = drive - lateral - turn;
        double backLeftPower = drive - lateral + turn;
        double backRightPower = drive + lateral - turn;
        frontLeft.setPower(frontLeftPower*multiplier);
        frontRight.setPower(frontRightPower*multiplier);
        backLeft.setPower(backLeftPower*multiplier);
        backRight.setPower(backRightPower*multiplier);
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
    
    public void drop2SuperPato(){
        superPato.setPower(-SUPER_PATO_POWER);
    }

    public void stopSuperPato(){
        superPato.setPower(0);
    }
    
    private void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
    }
    
    private void initAutoDrive(){
        double power = 0.5;
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(
            backLeft.isBusy() &&
            backRight.isBusy() &&
            frontLeft.isBusy() &&
            frontRight.isBusy()
        ){
            LinearOpMode programaAutonomo = (LinearOpMode) programa;
            programaAutonomo.sleep(50);
            
        }
            
    }
    
}
