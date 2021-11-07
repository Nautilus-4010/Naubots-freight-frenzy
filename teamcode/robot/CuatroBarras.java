package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class CuatroBarras implements Mechanism {

    public static final double POSITION_PICK_FREIGHT = 0;
    public static final double POSITION_LEVEL_ONE = 0.2;

    private DcMotor motor;
    private AnalogInput potentiometer;

    public void initializeHardware(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotor.class, "motor_cuatro_barras");
        potentiometer = hardwareMap.get(AnalogInput.class, "potenciometro");
    }

    public double getPosition(){
        return potentiometer.getVoltage() / potentiometer.getMaxVoltage();
    }

    public void setPosition(double targetPosition){
        double currentPosition = getPosition();
    }
}
