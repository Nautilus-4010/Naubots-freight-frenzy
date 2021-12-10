package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.AnalogInput;

public class Naubot {
    
    private double INTAKE_POWER = 1.0;
    private double SUPER_PATO_POWER = 1.0;
    private static final double TICKS_PER_CM = 14.1;
    // TODO: definir valores
    private final double SERVO_POSICION_UNO = 0.5;
    private final double SERVO_POSICION_DOS = 0.9;
    private final double SERVO_POSICION_TRES = 0.7;
    private final double SERVO_POSICION_CUATRO = 0.3;
    
    // Son constantes estaticas
    // TODO: definir valores
    public static final double BARRAS_POSICION_UNO = 0.6;
    public static final double BARRAS_POSICION_DOS = 0.5;
    public static final double BARRAS_POSICION_TRES = 0.4;
    public static final double BARRAS_POSICION_CUATRO = 0.3;
    
    // Como declararlo en teleop/autonomo
    // robot.setBarrasPosition(Naubot.BARRAS_POSICION_UNO);
    
    private OpMode programa;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    
    public DcMotor cuatroBarras;
    private DcMotor intake;
    private DcMotor superPato;
    //TODO: Personalizar nombre
    public Servo servo;
    
    public AnalogInput pot;
    
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
        // TODO: cambiar nombre
        servo = hardwareMap.get(Servo.class, "servo");
        // TODO: redefinir rango?
        // servo.setRange(min, max);
        
        // TODO: cambiar nombre
        pot = hardwareMap.get(AnalogInput.class, "pot");
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
    
    public void pickBackFreight(){
        intake.setPower(-INTAKE_POWER);
        servo.setPosition(SERVO_POSICION_DOS);
    }

    public void pickFrontFreight(){
        servo.setPosition(SERVO_POSICION_UNO);
    }

    public void stopInTake(){
        intake.setPower(0);
        servo.setPosition(SERVO_POSICION_TRES);
    }
    
    public void dropFreight(){
        servo.setPosition(SERVO_POSICION_CUATRO);
    }
    
    public void dropBlueSuperPato(){
        superPato.setPower(SUPER_PATO_POWER);
    }
    
    public void dropRedSuperPato(){
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
    
    private double getPotPosition(){
        return pot.getVoltage() / pot.getMaxVoltage();
    }
    
    // TODO: Declarar targets position (como constantes). 
    public void setCuatroBarrasPosition(double targetPosition){
        double currentPosition = getPotPosition();
        // Cambiar cuando se sepan los valores de la posicion
        double motorPower = 0.5;
        if(currentPosition<targetPosition){
            motor.setPower(motorPower);
        }else if(currentPosition>targetPosition){
            motor.setPower(-motorPower);
        }else{
            motor.setPower(0);
        }
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
