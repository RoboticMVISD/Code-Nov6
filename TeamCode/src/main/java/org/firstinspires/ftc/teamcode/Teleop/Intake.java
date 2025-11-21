package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Intake {
    OpMode op;
    public static DcMotor intake, secondaryIntake;

    final double INTAKE_SPEED = 1;
    final double BALL_SUPRESSOR_SPEED = 0.3;

    public void init(OpMode OP){
        op = OP;
        intake = op.hardwareMap.get(DcMotor.class, "intake");
        secondaryIntake = op.hardwareMap.get(DcMotor.class, "secondaryIntake");
    }

    public void loop(){
        campbellMoveIntakeConOne();
    }

    public void moveIntakeConOne(){
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        if (op.gamepad1.b){
            intake.setPower(INTAKE_SPEED);
        } else if (op.gamepad1.a){
            intake.setPower(-INTAKE_SPEED);
        } else if(op.gamepad1.y){
            secondaryIntake.setPower(1);
        } else if (op.gamepad1.x){
            secondaryIntake.setPower(-1);
        }else if (op.gamepad1.dpad_up){
            Shooter.leftShooter.setPower(-BALL_SUPRESSOR_SPEED);
            Shooter.rightShooter.setPower(-BALL_SUPRESSOR_SPEED);
        }else {
            intake.setPower(0);
            secondaryIntake.setPower(0);
        }
    }

    public void testIntakeConTwo(){
        if (op.gamepad2.b){
            secondaryIntake.setPower(INTAKE_SPEED);
            intake.setPower(1);
        } else if (op.gamepad2.x){
            secondaryIntake.setPower(-INTAKE_SPEED);
        } else {
            secondaryIntake.setPower(0);
            intake.setPower(0);
        }
    }

    //Just Campbell's Preferences for intake. Sub out for normal when he uses controller
    public void campbellMoveIntakeConOne(){
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        if (op.gamepad1.a){
            intake.setPower(INTAKE_SPEED);
        } else if (op.gamepad1.b){
            intake.setPower(-INTAKE_SPEED);
        } else if(op.gamepad1.y){
            secondaryIntake.setPower(1);
        } else if (op.gamepad1.x){
            secondaryIntake.setPower(-1);
        }else if (op.gamepad1.dpad_up){
            Shooter.leftShooter.setPower(-BALL_SUPRESSOR_SPEED);
            Shooter.rightShooter.setPower(-BALL_SUPRESSOR_SPEED);
        } else if (op.gamepad1.right_trigger > 0){
            intake.setPower(1);
            secondaryIntake.setPower(1);
        }else {
            intake.setPower(0);
            secondaryIntake.setPower(0);
        }
    }
}
