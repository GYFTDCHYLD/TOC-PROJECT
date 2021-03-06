package com.state;

import java.awt.Color;

import com.domain.UTuvcs;

public class Ignition extends State {
	
	public Ignition() {
		super("IGNITION-ON");
	}

	public Ignition(String name) {
		super(name);
	}
	
	public void Control(String command) {
		switch (command){   
			case "s": 	UTuvcs.getInputArray().add("s");
						UTuvcs.setState(new Off());
						UTuvcs.getSpeedometer().setText("");
						UTuvcs.getStateLabel().setForeground(Color.DARK_GRAY);
						radio();
						UTuvcs.setAccepted(true);
						logger.info(UTuvcs.getInputArray().toString().replaceAll(", ", ""));// save the string to a log file
						UTuvcs.getInputArray().clear();
						UTuvcs.getProcessString().setVisible(false); 
						break;
			case "h":	UTuvcs.getInputArray().add("h");
						UTuvcs.setState(new Hold());
						radio();
						UTuvcs.setAccepted(true);
						break;
			default: 	logger.warn("Invalid Commmand: " + command);
						UTuvcs.getStickShift().setSelectedIndex(-1); 
		}
	}

}
