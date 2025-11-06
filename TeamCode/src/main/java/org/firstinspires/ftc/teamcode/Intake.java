package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Intake {
    OpMode op;
    DcMotor intake;
    CRServo secondaryIntake = null;


    public void init(OpMode OP){
        op = OP;
        intake = op.hardwareMap.get(DcMotor.class, "intake");
        secondaryIntake = op.hardwareMap.get(CRServo.class, "secondaryIntake");
    }

    public void loop(){
        moveIntakeConOne();
    }

    public void moveIntakeConOne(){
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        if (op.gamepad1.b){
            intake.setPower(0.8);
        } else if (op.gamepad1.a){
            intake.setPower(0.4);
            secondaryIntake.setPower(1);
        } else if (op.gamepad1.y){
            intake.setPower(-.8);
        } else if (op.gamepad1.x){
           secondaryIntake.setPower(1);
        } else {
            intake.setPower(0);
            secondaryIntake.setPower(0);
        }
    }

}
