package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Blue Alliance")
public class AutonomoBlue extends LinearOpMode {
    private Naubot robot;

    @Override
    public void runOpMode(){
        robot = new Naubot(this);
        robot.initializeHardware();
        telemetry.update();
        waitForStart();
        
        
        robot.avanzar(115);
        robot.girar(56);
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
        robot.moverLateral(125);
        robot.avanzar(-120);
        robot.pickFreight();
         sleep(1300);
        
    }
}