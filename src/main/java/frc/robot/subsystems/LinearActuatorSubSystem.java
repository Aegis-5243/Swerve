// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LinearActuatorSubSystem extends SubsystemBase {

    // The Potentiometer
    // 6in Linear Actuator 
    public AnalogPotentiometer pot = new AnalogPotentiometer(3, 10, 0.0216);

    // The Motor for the robot
    public SparkMax cimMotor = new SparkMax(31, MotorType.kBrushed);

    /** Creates a new ExampleSubsystem. */
    public LinearActuatorSubSystem() {}

    public enum LinearActuatorDirection{
        UP(1),
        DOWN(-1);
        private final int dir;
        LinearActuatorDirection(int dir){
            this.dir = dir;
        }
        public int getDir(){
            return dir;
        }

    }
    public Command LinearActuatorMethodCommand(LinearActuatorDirection dir) {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        int direction = dir.getDir();
        return runEnd(
                () -> {
                    /* one-time action goes here */
                    // if it goes beyond the max height stop going up
                    if(direction > 0 && pot.get() >= Constants.LinearActuatorConstants.MAX){

                        cimMotor.set(0);

                    }
                    // if it goes bellow the min height stop going down
                    else if(direction < 0 && pot.get() <= Constants.LinearActuatorConstants.MIN){

                        cimMotor.set(0);

                    }
                    // go in the direction given at 20% the speed
                    else{

                        cimMotor.set(0.3 * direction);

                    }
                    

                }, () -> {
                    // when you let go of the button sets the motor speed to 0
                cimMotor.set(0);
            }
                );
        }

    /**
     * An example method querying a boolean state of the subsystem (for example, a
     * digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     */
    public boolean LinearActuatorCondition() {
        // Query some boolean state, such as a digital sensor.
        return false;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Potentiometer", pot.get());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        

    }
}
