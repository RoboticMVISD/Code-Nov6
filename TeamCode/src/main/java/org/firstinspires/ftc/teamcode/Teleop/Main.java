package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.testClasses.PIDFCoefficientTester;

@TeleOp (name = "Teleop 2025")
public class Main extends OpMode {

   MovementSystem movementSystem = new MovementSystem();
   Intake intake = new Intake();
   Shooter shooter = new Shooter();
   BatteryChecker batteryChecker = new BatteryChecker();

   public void init()
    {
        intake.init(this);
        movementSystem.init(this);
        shooter.init(this);
        batteryChecker.init(this);
    }

    @Override
    public void loop()
    {
        intake.loop();
        movementSystem.loop();
        try {
            shooter.loop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        batteryChecker.loop();
    }
}
