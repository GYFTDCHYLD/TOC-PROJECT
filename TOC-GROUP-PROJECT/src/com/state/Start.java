package com.state;

import java.awt.Color;

import com.domain.UTuvcs;

public class Start extends State{
	
	public Start() {
		super("ENGINE-STARTED");
	}

	public Start(String name) {
		super(name);
	}
	
	public void Control(String command) throws InterruptedException {
		switch (command){   
			case "s": 	UTuvcs.getInputArray().add("s");
						UTuvcs.setState(new Off());
						UTuvcs.getStickShift().setSelectedIndex(-1);
						UTuvcs.getSpeedometer().setText(""); 
						soundEffect("stopEngine");
						UTuvcs.getStateLabel().setForeground(Color.DARK_GRAY); 
						radio();
						UTuvcs.getSeatbelt().setVisible(false);
						UTuvcs.setAccepted(true); 
						logger.info(UTuvcs.getInputArray().toString().replaceAll(", ", ""));// save the string to a log file
						UTuvcs.getInputArray().clear();
						UTuvcs.getProcessString().setVisible(false);
						break;
			case "b":	UTuvcs.getInputArray().add("b");
						UTuvcs.setState(new Stationary());
						UTuvcs.getStickShift().setSelectedIndex(-1);
						UTuvcs.getSeatbelt().setVisible(false); 
						UTuvcs.setAccepted(true); 
						break;
			default: 	logger.warn("Invalid Commmand: " + command);
						UTuvcs.getStickShift().setSelectedIndex(2); 
		}
	}

}
