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
        double multiplier; 
        double drive = -gamepad2.left_stick_y;
        double lateral = gamepad2.left_stick_x;
        double turn = gamepad2.right_stick_x;
        // Multiplier control
        if(gamepad2.right_trigger > 0.5)
            multiplier = 1.0;
        else{
            multiplier = 0.85;
        }
        robot.move(drive, lateral, turn, multiplier);
        
        // Intake control
        if(gamepad1.right_trigger > 0.5)
            robot.pickFreight();
        else if(gamepad1.left_trigger > 0.5)
            robot.dropFreight();
        else
            robot.stopInTake();
            
        //SuperPato control
        if(gamepad2.a){
            robot.dropSuperPato();
        } else {
            robot.stopSuperPato();
        }
        
        // Cuatro barras control
        controlCuatroBarras();
        
        telemetry.update();
    }
    
    @Override
    public void stop(){}

    private void controlCuatroBarras(){
        double motor4bPower = 0.8;
        if(gamepad1.b) { 
            robot.cuatroBarras.setPower(motor4bPower);
        }else if(gamepad1.y){
            robot.cuatroBarras.setPower(-motor4bPower);
        }else{
            robot.cuatroBarras.setPower(0);
        }
    }
}