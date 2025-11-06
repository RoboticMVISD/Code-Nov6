package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.opencv.ml.ANN_MLP;

public class Shooter {
    OpMode op;
    CRServo stopper = null;
    DcMotor shooter;
    Servo angleChanger = null;

    public void init(OpMode OP){
        op = OP;
        stopper = op.hardwareMap.crservo.get("stopper");
        shooter = op.hardwareMap.dcMotor.get("shooter");
        angleChanger = op.hardwareMap.servo.get("angleChanger");
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        angleChanger.scaleRange(.4,.5);
    }

    public void loop(){
        moveShooterConTwo();
        moveAnglerConTwo();
    }

    public void moveShooterConTwo(){
        if (op.gamepad2.bWasPressed()){
            stopper.setPower(1);
        } else if (op.gamepad2.bWasReleased()){
            stopper.setPower(0);
        } else if (op.gamepad2.aWasPressed()){
            shooter.setPower(1);
        } else if (op.gamepad2.aWasReleased()){
            shooter.setPower(0);
        }
    }

    public void moveAnglerConTwo(){
        if (op.gamepad2.dpad_left){
            angleChanger.setPosition(0);
        } else if (op.gamepad2.dpad_right){
            angleChanger.setPosition(1);
        }
    }
}

