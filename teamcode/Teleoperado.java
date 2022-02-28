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


/////////////////////////////////////////////////////////

    @Override
    public void loop(){

    // Chasis control
    moveChasis(gamepad1);
        
        
    // Intake control
        controlIntake();

    //SuperPato control
        controlSuperPato(gamepad2);
      
    // Cuatro barras control
        
        controlCuatroBarras();
        logRobotStatus();
        
    // Brazo control
        brazoControl(gamepad1);
    }


/////////////////////////////////////////////////////////////
    
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

    private void brazoControl(Gamepad gamepad) {
        if(gamepad.dpad_up)
            robot.brazoGuardado();
        else if(gamepad.dpad_left)
            robot.brazoPick();
        else if(gamepad.dpad_down)
            robot.brazoDrop();
    }

    private void controlCuatroBarras(){
        double motor4bPower = gamepad2.right_stick_y;
        robot.cuatroBarras.setPower(motor4bPower/2);
    }
    
    private void controlIntake() {
        if(gamepad2.left_trigger > 0 && gamepad2.right_trigger > 0)
            robot.exitFreight();
        else if(gamepad2.left_trigger > 0)
            robot.dropFreight();
        else if(gamepad2.right_trigger > 0 || gamepad1.right_trigger > 0)
            robot.pickFreight();
        else
            robot.stopInTake();
    }
    
    private void controlSuperPato(Gamepad gamepad) {
        if(gamepad.dpad_right)
            robot.dropBlueSuperPato();
        else if(gamepad.dpad_left)
            robot.dropRedSuperPato();
        else if(gamepad.dpad_down)
            robot.dropBlueSuperPatoRemix();
        else if(gamepad.dpad_up) 
            robot.dropRedSuperPatoRemix();
        else
            robot.stopSuperPato();
    }
    
    private void moveChasis(Gamepad gamepad) {
        double powerMultiplier = 1;
        double drive = -gamepad.left_stick_y;
        double lateral = gamepad.left_stick_x;
        double turn = gamepad.right_stick_x;
        robot.move(drive, lateral, turn, powerMultiplier);
    }
}