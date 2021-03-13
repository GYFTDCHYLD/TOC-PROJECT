package com.state;

import com.domain.UTuvcs;

public class Reverse  extends State  {
	
	public Reverse() {
		super("IN-REVERSE-MOTION");
	}

	public Reverse(String name) {
		super(name);
	}
	
	public void Control(String command) throws InterruptedException { 
		switch (command){  
			case "a": 	UTuvcs.getInputArray().add("a");  
						accelerateDecelerate("trottle");
						UTuvcs.setAccepted(true);
						soundEffect("gas");
						break;
			case "p":	UTuvcs.getInputArray().add("p");
						accelerateDecelerate("brake");
						UTuvcs.setAccepted(true);
						break; 
			case "h":	UTuvcs.getInputArray().add("h");
						UTuvcs.setSpeed(0);
						UTuvcs.setState(new Stationary());
						UTuvcs.getStickShift().setSelectedIndex(-1); 
						UTuvcs.getReverseCam().setVisible(false);
						UTuvcs.getGearIndicatior().setVisible(false);
						radio();
						UTuvcs.setAccepted(true);
						break;
			case "z":	UTuvcs.getInputArray().add("z");
						UTuvcs.setState(new Stationary());
						UTuvcs.getStickShift().setSelectedIndex(-1); 
						UTuvcs.getReverseCam().setVisible(false);
						UTuvcs.getGearIndicatior().setVisible(false);
						radio();
						UTuvcs.setAccepted(true);
						break;
			default: 	logger.warn("Invalid Commmand: " + command);
						reject();
						UTuvcs.getStickShift().setSelectedIndex(3); 
		}
	}

}
