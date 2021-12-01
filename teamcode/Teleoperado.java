package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.robot.FTCRobot;
import org.firstinspires.ftc.teamcode.robot.CuatroBarras;
import org.firstinspires.ftc.teamcode.utils.FPSCounter;

@TeleOp(name="Teleoperado")
public class Teleoperado extends OpMode{
    private FTCRobot robot;
    private FPSCounter fps;

    private double cuatroBarrasPosition;

    @Override
    public void init(){
        robot = new FTCRobot(this);
        fps = new FPSCounter();
        robot.initializeMechanisms();
        cuatroBarrasPosition = CuatroBarras.POSITION_PICK_FREIGHT;
        //robot.cuatroBarras.setPosition(cuatroBarrasPosition);
        telemetry.update();
    }
    
    @Override
    public void init_loop(){}
    
    @Override
    public void start(){
        fps.startTimer();
    }
    
    @Override
    public void loop(){
        double drive = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        robot.chasis.move(drive, lateral, turn);
        cuatroBarrasPosition();
        // Intake control
        //robot.cuatroBarras.setPosition(cuatroBarrasPosition);
        if(gamepad1.right_trigger > 0.5){
            robot.intake.pickFreight();
        }else if(gamepad1.left_trigger > 0.5){
            robot.intake.dropFreight();
        }else{
            robot.intake.stopInTake();
        }
        
        //SuperPato control
        if(gamepad1.a){
            robot.superPato.dropSuperPato();
        }else{
            robot.superPato.stopSuperPato();
        }
        
        // Cuatro barras control
        setDirection();
        telemetry.addData("FPS", fps.getUpdatedFPS());
        robot.logMechanismStatus();
        telemetry.update();
        
    }
    
    @Override
    public void stop(){}

    private void cuatroBarrasPosition(){
        if(gamepad1.b){
            cuatroBarrasPosition = CuatroBarras.POSITION_LEVEL_ONE;
        }else if(gamepad1.x){
            cuatroBarrasPosition = CuatroBarras.POSITION_LEVEL_TWO;
        }else if(gamepad1.y){
            cuatroBarrasPosition = CuatroBarras.POSITION_LEVEL_THREE;
        }else if(gamepad1.a){
            cuatroBarrasPosition = CuatroBarras.POSITION_CAPPING;
        }else{
            cuatroBarrasPosition = CuatroBarras.POSITION_PICK_FREIGHT;
        }
    }

    private void setDirection(){
        double motor4bPower = 0.8;
        if(gamepad1.a) { 
            robot.cuatroBarras.motor.setPower(motor4bPower);
        }else if(gamepad1.y){
            robot.cuatroBarras.motor.setPower(-motor4bPower);
        }else{
            robot.cuatroBarras.motor.setPower(0);
        }
    }
}