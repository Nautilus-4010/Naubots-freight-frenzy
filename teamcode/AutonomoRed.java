package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="Autonomo")
public class Autonomo extends LinearOpMode {
    private Naubot robot;

    @Override
    public void runOpMode(){
        robot = new Naubot(this);
        robot.initializeHardware();
        telemetry.update();
        waitForStart();
       //robot.avanzar(10);
        robot.moverLateral(120);
        //robot.dropSuperPato();
        //sleep(10000);
        //robot.avanzar(50);
        //robot.moverLateral(20);
    }
}