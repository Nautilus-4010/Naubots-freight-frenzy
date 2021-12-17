package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Blue Pato Alliance")
public class AutonomoBluePato extends LinearOpMode {
    private Naubot robot;

    @Override
    public void runOpMode(){
        robot = new Naubot(this);
        robot.initializeHardware();
        telemetry.update();
        waitForStart();
        
        
        robot.avanzar(100);
        robot.girar(-56);
        robot.cuatroBarras.setPower(-0.3);
         sleep(1450);
         robot.cuatroBarras.setPower(0);
        robot.dropFreight();
         sleep(1000);
        robot.stopInTake();
         sleep(100);
        robot.cuatroBarras.setPower(0.3);
         sleep(1300);
        robot.cuatroBarras.setPower(0);
        robot.avanzar(-80);
        robot.moverLateral(-65);
        robot.dropBlueSuperPato();
         sleep(100);
        robot.moverLateral(65);
        
        
    }
}