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

/////////////////////////////////////////////////////////

    @Override
    public void loop(){

    // Chasis control

        double powerMultiplier; 
        double drive = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        if(gamepad1.right_trigger > 0.5)
            powerMultiplier = 1.0;
        else
            powerMultiplier = 0.6;
        if(gamepad1.dpad_up)
            drive = 0.8;
        else if(gamepad1.dpad_down)
            drive = -0.8;
        if(gamepad1.dpad_left)
            lateral = -0.8;
        else if(gamepad1.dpad_right)
            lateral = 0.8;
        robot.move(drive, lateral, turn, powerMultiplier);
        
    // Intake control
    
        if(gamepad2.left_trigger > 0.3 & gamepad2.right_trigger > 0.3)
            robot.exitFreigh();
        else if(gamepad2.left_trigger > 0.5)
            robot.dropFreight();
        else if(gamepad2.right_trigger > 0.5)
            robot.pickFreight();
        else
            robot.stopInTake();

    // Brazo control
    
        double brazoPower = gamepad2.left_stick_x;
        robot.controlBrazo(brazoPower);
            
    //SuperPato control
        
        if(gamepad2.dpad_left)
            robot.dropBlueSuperPato();
        else if(gamepad2.dpad_right)
            robot.dropRedSuperPato();
        else if(gamepad2.dpad_up)
            robot.dropBlueSuperPatoRemix();
        else if(gamepad2.dpad_down)
            robot.dropRedSuperPatoRemix();
        else
            robot.stopSuperPato();
            
    //4Barras Automatico control
    
      /*  
        if(gamepad2.a)
            robot.setCuatroBarrasPosition(Naubot.BARRAS_POSICION_UNO);
        else if(gamepad2.b)
            robot.setCuatroBarrasPosition(Naubot.BARRAS_POSICION_DOS);
        else if(gamepad2.y)
            robot.setCuatroBarrasPosition(Naubot.BARRAS_POSICION_TRES);
        else if(gamepad2.x)
            robot.setCuatroBarrasPosition(Naubot.BARRAS_POSICION_CUATRO);
        else
            robot.setCuatroBarrasPosition(Naubot.BARRAS_POSICION_CINCO);
      */
      
    // Cuatro barras control
        
        controlCuatroBarras();
        logRobotStatus();
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
        
        telemetry.addData("Posicion potenciometro: %.3f", robot.getPotPosition());
        
        telemetry.update();
    }

/////////////////////////////////////////////////////////////

    private void controlCuatroBarras(){
        double motor4bPower = gamepad2.right_stick_y;
        robot.cuatroBarras.setPower(motor4bPower/2);
    }
}