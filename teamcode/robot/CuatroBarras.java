package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class CuatroBarras implements Mechanism {

    public static final double POSITION_PICK_FREIGHT = 0;
    public static final double POSITION_LEVEL_ONE = 0.2;
    public static final double POSITION_LEVEL_TWO = 0.4;
    public static final double POSITION_LEVEL_THREE = 0.6;
    public static final double POSITION_CAPPING = 0.8;

    public DcMotor motor;
    private AnalogInput potentiometer;

    public void initializeHardware(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotor.class, "motor4b");
        potentiometer = hardwareMap.get(AnalogInput.class, "potenciometro");
    }

    public double getPosition(){
        return potentiometer.getVoltage() / potentiometer.getMaxVoltage();
    }

    public void setPosition(double targetPosition){
        double currentPosition = getPosition();
        double motorPower = 0.5;
        if(currentPosition<targetPosition){
            motor.setPower(motorPower);
        }else if(currentPosition>targetPosition){
            motor.setPower(-motorPower);
        }else{
            motor.setPower(0);
        }
    }



    // Codigo para usar el mecanismo de cuatro barras manualmente:

    /*
    private DcMotor motor;

    public void initializeHardware(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotor.class, "motor4b");
    }

    public void setDirection(){
        double motor4bPower = 0.8;
        if (gamepad1.b) { 
            robot.cuatroBarras.motor.setPower(motor4bPower);
        }else if(gamepad1.y){
            robot.cuatroBarras.motor.setPower(-motor4bPower);
        }else{
            intake.setPower(0);
        }
    }
    */

}
