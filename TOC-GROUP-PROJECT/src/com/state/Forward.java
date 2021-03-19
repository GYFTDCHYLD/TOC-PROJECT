package com.state;

import com.domain.UTuvcs;

public class Forward  extends State {
	
	public Forward() {
		super("IN-FORWARD-MOTION");
	}

	public Forward(String name) {
		super(name);
	}
	
	public void Control(String command) throws InterruptedException {
		switch (command){  
			case "a":	UTuvcs.getInputArray().add("a");  
						accelerateDecelerate("trottle");
						UTuvcs.setAccepted(true);
						soundEffect("gas2");
						break;
			case "c":	UTuvcs.getInputArray().add("c"); 
						UTuvcs.setState(new Cruise());
						UTuvcs.setAccepted(true);
						break; 
			case "p":	UTuvcs.getInputArray().add("p");
						accelerateDecelerate("brake");
						UTuvcs.setAccepted(true);
						break;
			case "z":	UTuvcs.getInputArray().add("z");
						UTuvcs.setState(new Stationary());
						UTuvcs.getStickShift().setSelectedIndex(-1);  
						UTuvcs.getGearIndicatior().setVisible(false);
						UTuvcs.setAccepted(true);
						break; 
			default: 	logger.warn("Invalid Commmand: " + command);
						reject(); 
		}
	}

}
