package com.state;

import java.awt.Color;

import com.domain.UTuvcs;

public class Off extends State {
	
	public Off() {
		super("OFF");
	}

	public Off(String name) {
		super(name);
	}
	
	public void Control(String command) throws InterruptedException { 
		switch (command){  
			case "s": 	UTuvcs.getInputArray().add("s");
						UTuvcs.setState(new Ignition());
						soundEffect("ignition");  
						UTuvcs.getStateLabel().setForeground(Color.WHITE); 
						radio();
						UTuvcs.setAccepted(true); 
						UTuvcs.getProcessString().setVisible(true); 
						break;
			case "h":	UTuvcs.getInputArray().add("h");
						UTuvcs.setState(new Hold());
						UTuvcs.getStateLabel().setForeground(Color.WHITE);
						UTuvcs.setAccepted(true); 
						UTuvcs.getProcessString().setVisible(true);
						break;
			default: 	logger.warn("Invalid Commmand: " + command); 
						reject("rejected");
						UTuvcs.getStickShift().setSelectedIndex(-1);  
		}
	}

}
