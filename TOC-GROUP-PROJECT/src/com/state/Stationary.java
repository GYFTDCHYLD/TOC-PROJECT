package com.state;

import com.domain.UTuvcs;

public class Stationary  extends State {

	
	public Stationary() {
		super("STATIONARY-POSITION");
	}
	
	public Stationary(String name) {
		super(name);
	}
	
	public void Control(String command) throws InterruptedException { 
		switch (command){  
			case "a": 	UTuvcs.getInputArray().add("a");
						UTuvcs.setAccepted(true);
						soundEffect("gas");
						break;
			case "d":	UTuvcs.getInputArray().add("d");
						UTuvcs.setSpeed(UTuvcs.getSpeed() + 1);
						UTuvcs.setState(new Forward());
						UTuvcs.getGear().setText("1");
						UTuvcs.getGearIndicatior().setBounds(524, 523, 20, 20);// green indicator to the right of the gear shift/stick
						UTuvcs.getGearIndicatior().setVisible(true);
						UTuvcs.setAccepted(true);
						break;
			case "r":	UTuvcs.getInputArray().add("r");
						UTuvcs.setSpeed(UTuvcs.getSpeed() + 1);
						UTuvcs.setState(new Reverse());
						UTuvcs.getReverseCam().setVisible(true);
						UTuvcs.getGear().setText("R");
						UTuvcs.getGearIndicatior().setBounds(524, 541, 20, 20);
						UTuvcs.getGearIndicatior().setVisible(true);
						radio();
						UTuvcs.setAccepted(true);
						break;
			case "n":	UTuvcs.getInputArray().add("n");
						UTuvcs.setState(new Start());
						seatbelt();
						UTuvcs.setAccepted(true);
						break;
			default: 	logger.warn("Invalid Commmand: " + command);
						reject();
		}
	}

}
