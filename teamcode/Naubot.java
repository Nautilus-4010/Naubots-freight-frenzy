package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.AnalogInput;

////////////////////////////////////////////////////////////////////////////////

public class Naubot {
    
    //Valores Motores:
    private double INTAKE_POWER = 1.0;
    private double SUPER_PATO_POWER = 1.0;

    //Valores servos:
    private final double CAJA_POSICION_UNO = 0.73;
    private final double CAJA_POSICION_DOS = 0.20;
    private final double CAJA_POSICION_TRES = 0.60;
    
    private final double BRAZO_POSICION_UNO = 0.56;
    private final double BRAZO_POSICION_DOS = 0.6;
    private final double BRAZO_POSICION_TRES = 0.66;
    
    private final double GARRA_POSICION_UNO = 0.56;
    private final double GARRA_POSICION_DOS = 0.6;

    // TODO: definir valores
    //Niveles 4 barras:
    public static final double BARRAS_POSICION_UNO = 0.0293;
    public static final double BARRAS_POSICION_DOS = 0.1;
    public static final double BARRAS_POSICION_TRES = 0.2;
    public static final double BARRAS_POSICION_CUATRO = 0.28;
    public static final double BARRAS_POSICION_CINCO = 0.1;
    
    //Data:
    private double targetPosition;
    private static final double TICKS_PER_CM = 15.76;
    
    private OpMode programa;

    //Motores Chasis:
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    
    //Motores
    public DcMotor cuatroBarras;
    private DcMotor intake;
    private DcMotor superPato;
    private DcMotor superPato2;

    //Servos:
    public Servo caja;
    public Servo brazo;
    public Servo garra;
    public CRServo intakeServo;
    
    //InPut(s)
    
////////////////////////////////////////////////////////////////////////////////

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
        superPato2 = hardwareMap.get(DcMotor.class, "motorSuperPato2");
        intakeServo = hardwareMap.get(CRServo.class, "mIntake");
        caja = hardwareMap.get(Servo.class, "caja");
        brazo = hardwareMap.get(Servo.class, "brazo");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
    
    public void girar (double distancia){
        int target = (int) Math.round(distancia * TICKS_PER_CM);
        resetEncoders();
        frontLeft.setTargetPosition(target);
        frontRight.setTargetPosition(-target);
        backLeft.setTargetPosition(target);
        backRight.setTargetPosition(-target);
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
        intake.setPower(-INTAKE_POWER);
        caja.setPosition(CAJA_POSICION_UNO);
        intakeServo.setPower(-1);
        
    }

    public void dropFreight(){
        caja.setPosition(CAJA_POSICION_DOS);
    }
    
    public void pickElement(){
        garra.setPosition(GARRA_POSICION_UNO);
    }
    
    public void dropElement(){
        garra.setPosition(GARRA_POSICION_DOS);
    }
    
    public void brazoGuardado(){
        brazo.setPosition(BRAZO_POSICION_UNO);
    }
    
    public void brazoPick(){
        brazo.setPosition(BRAZO_POSICION_DOS);
    }
    
    public void brazoDrop(){
        brazo.setPosition(BRAZO_POSICION_TRES);
    }

    public void stopInTake(){
        intake.setPower(0);
        caja.setPosition(CAJA_POSICION_TRES);
        intakeServo.setPower(0);
    }

    public void exitFreight(){
        intake.setPower(INTAKE_POWER);
        caja.setPosition(CAJA_POSICION_TRES);
    }
    
    public void dropBlueSuperPato(){
        superPato.setPower(SUPER_PATO_POWER);
    }

    public void dropBlueSuperPatoRemix(){
        superPato.setPower(-SUPER_PATO_POWER);
    }
    
    public void dropRedSuperPato(){
        superPato2.setPower(SUPER_PATO_POWER);
    }

    public void dropRedSuperPatoRemix(){
        superPato2.setPower(-SUPER_PATO_POWER);
    }

    public void stopSuperPato(){
        superPato.setPower(0);
        superPato2.setPower(0);
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
        double power = 0.8;
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