package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class SimpleAuto extends LinearOpMode {
    DcMotor frontRight, frontLeft, backRight, backLeft, Shooter;


    @Override
    public void runOpMode() throws InterruptedException {
        MapHardware();

        waitForStart();

        frontRight.setPower(1);
        frontLeft.setPower(1);
        backLeft.setPower(-1);
        backRight.setPower(1);
        Thread.sleep(300);
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        while (opModeIsActive()) {

        }
    }

    public void MapHardware () {
        frontRight = hardwareMap.get(DcMotor.class,  "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class,  "frontLeft");
        backRight = hardwareMap.get(DcMotor.class,  "backRight");
        backLeft = hardwareMap.get(DcMotor.class,  "backLeft");
    }
}
