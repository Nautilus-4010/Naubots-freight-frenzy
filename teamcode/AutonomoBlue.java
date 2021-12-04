@Autonomous(name="Blue Alliance")
public class AutonomoBlue extends LinearOpMode {
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