package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Naubot;


@TeleOp(name="Teleoperado")
public class Teleoperado extends OpMode{
    private Naubot robot;

    private double cuatroBarrasPosition;

    @Override
    public void init(){
        robot = new Naubot(this);
        robot.initializeHardware();
        telemetry.update();
    }
    
    @Override
    public void init_loop(){}
    
    @Override
    public void start(){
        
    }
    
    @Override
    public void loop(){
        double powerMultiplier; 
        double drive = -gamepad2.left_stick_y;
        double lateral = gamepad2.left_stick_x;
        double turn = gamepad2.right_stick_x;
        if(gamepad2.right_trigger > 0.5)
            powerMultiplier = 1.0;
        else
            powerMultiplier = 0.5;
        if(gamepad2.dpad_up)
            drive = 0.8;
        else if(gamepad2.dpad_down)
            drive = -0.8;
        if(gamepad2.dpad_left)
            lateral = -0.8;
        else if(gamepad2.dpad_right)
            lateral = 0.8;
        robot.move(drive, lateral, turn, powerMultiplier);
        
        // Intake control
        if(gamepad1.left_trigger > 0.5)
            robot.pickFreight();
        else if(gamepad1.right_trigger > 0.5)
            robot.dropFreight();
        else
            robot.stopInTake();
            
        //SuperPato control
        if(gamepad2.a){
            robot.dropSuperPato();
        } else if(gamepad2.y){
            robot.drop2SuperPato();
        }else {
            robot.stopSuperPato();
        }
        
        // Cuatro barras control
        controlCuatroBarras();
        logRobotStatus();
    }
    
    @Override
    public void stop(){}
    
    private void logRobotStatus(){
        telemetry.addData("POWER", "");
        telemetry.addData("", String.format("%.2f | %.2f", robot.frontLeft.getPower(), robot.frontRight.getPower()));
        telemetry.addData("", String.format("%.2f | %.2f", robot.backLeft.getPower(), robot.backRight.getPower()));
        
        telemetry.addData("ENCODERS", "");
        telemetry.addData("Front right", "" + robot.frontRight.getCurrentPosition());
        telemetry.addData("Front left", "" + robot.frontLeft.getCurrentPosition());
        telemetry.addData("Back left", "" + robot.backLeft.getCurrentPosition());
        telemetry.addData("Back right", "" + robot.backRight.getCurrentPosition());
        
        telemetry.update();
    }

    private void controlCuatroBarras(){
        double motor4bPower = 0.8;
        if(gamepad1.y) { 
            robot.cuatroBarras.setPower(motor4bPower);
        }else if(gamepad1.a){
            robot.cuatroBarras.setPower(-motor4bPower);
        }else{
            robot.cuatroBarras.setPower(0);
        }
    }
}