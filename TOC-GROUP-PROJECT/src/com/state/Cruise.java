package com.state;

import com.domain.UTuvcs;

public class Cruise  extends State {
	
	public Cruise() {
		super("CRUISE-CONTROL-ENGAGED");
	}

	public Cruise(String name) {
		super(name);
	}
	
	public void Control(String command) {
		switch (command){  
			case "a": 	UTuvcs.getInputArray().add("a");
						UTuvcs.setAccepted(true);
						break;
			case "p":	UTuvcs.getInputArray().add("p");
						UTuvcs.setState(new Forward());
						UTuvcs.getStickShift().setSelectedIndex(1); 
						UTuvcs.setAccepted(true);
						break;
			default: 	logger.warn("Invalid Commmand: " + command);
						reject();
						UTuvcs.getStickShift().setSelectedIndex(0); 
				 
		}
	}

}
