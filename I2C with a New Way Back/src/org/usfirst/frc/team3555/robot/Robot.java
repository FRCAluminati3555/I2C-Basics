package org.usfirst.frc.team3555.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {

	private I2C i2cBus;
	
    public Robot() {
    	i2cBus = new I2C(I2C.Port.kOnboard, 0x0B);
    }

    public void operatorControl() {
    	sendRequest(I2C_Request.Request_0);
    	Timer.delay(0.5);
    	byte[] data = readData(2);
    	for(int i = 0; i < data.length; i ++) {
    		SmartDashboard.putNumber("Byte[" + i + "]", data[i]);
    	}

    	short resposeInt = (short) (((short)(data[0] << 8) & 0xFF00) | ((short)data[1] & 0x00FF));
		SmartDashboard.putNumber("Int Version", resposeInt);
    	
    	
//        while (isOperatorControl() && isEnabled()) {
//        	
//        	
//        }
    }
    
    public void sendRequest(I2C_Request resquest) {
    	i2cBus.write(0, resquest.id);
    }
    
    public byte[] readData(int size) {
    	byte[] data = new byte[size];
    	i2cBus.readOnly(data, size);
    	return data;
    }
    
    private static enum I2C_Request {
    	Request_0(0), Request_1('f');
    	
    	private byte id;
    	private I2C_Request(int id) {
    		this.id = (byte) id;
    	}
    }
    
}
