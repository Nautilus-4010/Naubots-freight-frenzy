package org.firstinspires.ftc.teamcode.robot;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class RobotVision implements Mechanism {
    
    public final static double MM_PER_INCH = 25.40 ;

    VuforiaLocalizer vuforia;
    VuforiaTrackables targetsFreightFrenzy;
    
    public RobotVision(){}
    
    public void initializeHardware(HardwareMap hardwareMap){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "ATjrkEL/////AAABmfR2/BPftkOFvL9kl5ElbHswfU6Tuno4QSB4aHpVUmWaWqKdEUps2CsnGbmjoGqMAfOjyPlhrew8njlemEsarH9XKySF9i0egaUhOiT2fE0MivatYaT037ZwPe1bOkI1GGmd2CsWL8GeupcT91XQkGhRcMyTS3ZfmDYu1/HmcRxCy4zxwbiyPVcoHtsh+KPfjI29mv9YfMStiB4/o8FgefPbTGtX6L9zeoyUemNIMN1WcaMi6wSM7rB7kF3VnUJCrXAca6YmFNEr6GEdJX4G7JhO5EiD6K/e1+wZ0fLtWiQDWe09Bgxxpp2n+qHeccA06zA8nNTo2F07UORoM40ZK29vMj4eh0GjyNMAOmWcuQeI";
        parameters.useExtendedTracking = false;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        targetsFreightFrenzy = this.vuforia.loadTrackablesFromAsset("FreightFrenzy");
        targetsFreightFrenzy.get(0).setName("Barco");
        targetsFreightFrenzy.get(1).setName("Avion");
        targetsFreightFrenzy.get(2).setName("Camiones");
        targetsFreightFrenzy.get(3).setName("Camion");
        targetsFreightFrenzy.activate();
    }
    
    public String getTargetName(){
        String targetName = null;
        double  targetRange = 0;
        double  targetBearing = 0;
        for (VuforiaTrackable trackable : targetsFreightFrenzy){
            VuforiaTrackableDefaultListener trackableListener = (VuforiaTrackableDefaultListener) trackable.getListener();
            if (trackableListener.isVisible()){
                OpenGLMatrix targetPose;
                targetPose = trackableListener.getVuforiaCameraFromTarget();

                if (targetPose != null){
                    targetName  = trackable.getName();
                    VectorF trans = targetPose.getTranslation();
                    double targetX = trans.get(0) / MM_PER_INCH;
                    double targetY = trans.get(2) / MM_PER_INCH;
                    targetRange = Math.hypot(targetX, targetY);
                    targetBearing = Math.toDegrees(Math.asin(targetX / targetRange));
                    break;
                }
            }
        }

        if (targetName != null) {
            return targetName;
        } else {
            return "Target not found";
        }
    }
}
