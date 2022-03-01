package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
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

/////////////////////////////////////////////////////////

    @Override
    public void loop(){

    // Chasis control
    moveChasis(gamepad1);
        
        
    // Intake control
        if(gamepad2.left_trigger > 0 && gamepad2.right_trigger > 0)
            robot.exitFreight();
        else if(gamepad2.left_trigger > 0)
            robot.dropFreight();
        else if(gamepad2.right_trigger > 0 || gamepad1.right_trigger > 0)
            robot.pickFreight();
        else
            robot.stopInTake();
        
        //(gamepad1.left_trigger > 0)
           // robot.dropFreight();

    //SuperPato control
        
        if(gamepad2.dpad_right)
            robot.dropBlueSuperPato();
        else if(gamepad2.dpad_left)
            robot.dropRedSuperPato();
        else if(gamepad2.dpad_down)
            robot.dropBlueSuperPatoRemix();
        else if(gamepad2.dpad_up) 
            robot.dropRedSuperPatoRemix();
        else
            robot.stopSuperPato();
      
    // Cuatro barras control
        
        controlCuatroBarras();
        logRobotStatus();
        
    // Brazo control
    
        //double brazoPower = gamepad2.left_stick_x;
        //robot.controlBrazo(brazoPower); 
        
        if(gamepad1.dpad_up)
            robot.brazoGuardado();
        else if(gamepad1.dpad_left)
            robot.brazoPick();
        else if(gamepad1.dpad_down)
            robot.brazoDrop();
    
    
    
    }
    
    
    

/////////////////////////////////////////////////////////////

    @Override
    public void stop(){}
    
    private void logRobotStatus(){
        telemetry.addData("Servo Caja: ", robot.caja.getPosition());
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

/////////////////////////////////////////////////////////////

    private void controlCuatroBarras(){
        double motor4bPower = gamepad2.right_stick_y;
        robot.cuatroBarras.setPower(motor4bPower/2);
    }
    
    private void moveChasis(Gamepad gamepad) {
        double powerMultiplier = 1;
        double drive = -gamepad.left_stick_y;
        double lateral = gamepad.left_stick_x;
        double turn = gamepad.right_stick_x;
        robot.move(drive, lateral, turn, powerMultiplier);
    }
}