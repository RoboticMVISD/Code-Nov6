package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {
    OpMode op;
    public static DcMotorEx leftShooter, rightShooter;
    CRServo turretRotator, leftFeeder, rightFeeder;
    Servo blocker;

    //Max Velocity range is -2500 to 2500
    double SPIN_UP_VELOCITY = 1000;
    double MIN_PWR_BLOCKER = -1;
    double MAX_PWR_BLOCKER = 1;
    double ROTATE_PWR = 0.5;

    //PIDF Variables
    public static double proportional = 300;
    public static double integral = 0;
    public static double derivative = 0.001;
    public static double feedForward = 10;
    public static PIDFCoefficients pidfCoefficients = new PIDFCoefficients(proportional, integral, derivative, feedForward);

    public void init(OpMode OP){
        op = OP;

        leftShooter = op.hardwareMap.get(DcMotorEx.class, "leftShooter");
        leftShooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        leftShooter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftShooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftShooter.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        rightShooter = op.hardwareMap.get(DcMotorEx.class, "rightShooter");
        rightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightShooter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightShooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightShooter.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        leftFeeder = op.hardwareMap.crservo.get("leftFeeder");

        rightFeeder = op.hardwareMap.crservo.get("rightFeeder");
        rightFeeder.setDirection(CRServo.Direction.REVERSE);

        turretRotator = op.hardwareMap.crservo.get("turretRotator");
        turretRotator.setDirection(DcMotorSimple.Direction.REVERSE);

        blocker = op.hardwareMap.servo.get("blocker");
        blocker.setDirection(Servo.Direction.REVERSE);
    }

    public void loop() throws InterruptedException {
        shooterConOne();
        shooterTesterConTwo();
        rotateTurret();
    }

    public void shooterConTwo(){
        if (op.gamepad2.y){
            autoAimTurret();
        }else if (op.gamepad2.right_bumper){
            rightShooter.setVelocity(SPIN_UP_VELOCITY);
            leftShooter.setVelocity(SPIN_UP_VELOCITY);
        } else if (op.gamepad2.dpad_up){
            leftFeeder.setPower(MAX_PWR_BLOCKER);
            rightFeeder.setPower(MAX_PWR_BLOCKER);
        } else if (op.gamepad2.dpad_down){
            leftFeeder.setPower(MIN_PWR_BLOCKER);
            rightFeeder.setPower(MIN_PWR_BLOCKER);
        } else if (op.gamepad2.left_bumper){
            leftShooter.setPower(-SPIN_UP_VELOCITY *0.5);
            rightShooter.setPower(-SPIN_UP_VELOCITY *0.5);
        }else {
            rightShooter.setPower(0);
            leftShooter.setPower(0);
            leftFeeder.setPower(0);
            rightFeeder.setPower(0);
        }
        op.telemetry.addLine("Spin Power: " + SPIN_UP_VELOCITY);
    }

    public void shooterConOne(){
        if (op.gamepad1.dpad_left){
            blocker.setPosition(0);
        } else if (op.gamepad1.dpad_right){
            blocker.setPosition(1);
        }
    }

    public void autoAimTurret(){

    }

    public void rotateTurret(){
        if (op.gamepad2.right_trigger > 0) {
            turretRotator.setPower(op.gamepad2.right_trigger * ROTATE_PWR);
        } else if(op.gamepad2.left_trigger > 0){
            turretRotator.setPower(-op.gamepad2.left_trigger * ROTATE_PWR);
        }else if (op.gamepad2.dpad_left){
            turretRotator.setPower(-.1);
        }else if (op.gamepad2.dpad_right){
            turretRotator.setPower(.1);
        }else {
            turretRotator.setPower(0);
        }
    }

    public void shooterTesterConTwo() throws InterruptedException {
        if (op.gamepad2.dpadUpWasPressed()){
            SPIN_UP_VELOCITY += 100;
        } else if (op.gamepad2.dpadDownWasPressed()){
            SPIN_UP_VELOCITY -= 100;
        } else if (op.gamepad2.dpadLeftWasPressed()){
            SPIN_UP_VELOCITY -= 50;
        } else if (op.gamepad2.dpadRightWasPressed()){
            SPIN_UP_VELOCITY += 50;
        } else if (op.gamepad2.right_bumper){
            rightShooter.setVelocity(SPIN_UP_VELOCITY);
            leftShooter.setVelocity(SPIN_UP_VELOCITY);
        } else if (op.gamepad2.left_bumper) {
            rightShooter.setVelocity(-SPIN_UP_VELOCITY);
            leftShooter.setVelocity(-SPIN_UP_VELOCITY);
        }else {
            leftShooter.setVelocity(0);
            rightShooter.setVelocity(0);
        }

        op.telemetry.addLine("Spin Power Left: " + leftShooter.getVelocity() + " \nSpin Power Right: " + leftShooter.getVelocity() + "\nWhat Power Should be: " + SPIN_UP_VELOCITY);
    }

}
