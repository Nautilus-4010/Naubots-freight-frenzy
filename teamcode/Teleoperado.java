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
        robot.cuatroBarras.setPosition(cuatroBarrasPosition);
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
        robot.cuatroBarras.setPosition(cuatroBarrasPosition);
        telemetry.addData("FPS", fps.getUpdatedFPS());
        robot.logMechanismStatus();
        telemetry.update();
    }
    
    @Override
    public void stop(){}

    private void cuatroBarrasPosition(){
        if(gamepad1.a)
            cuatroBarrasPosition = CuatroBarras.POSITION_LEVEL_ONE;
        else
            cuatroBarrasPosition = CuatroBarras.POSITION_PICK_FREIGHT;
    }
}