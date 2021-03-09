package com.state;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import com.domain.UTuvcs;

public class Hold extends State {
	
	public Hold() {
		super("HOLD");
	}

	public Hold(String name) {
		super(name);
	}
	
	public void Control(String command) throws InterruptedException { 
		switch (command){  
			case "s": 	UTuvcs.getInputArray().add("s");
						UTuvcs.setState(new Start());
						soundEffect("startEngine");
						UTuvcs.getStateLabel().setForeground(Color.WHITE);
						radio();
						trottle();
						seatbelt();
						UTuvcs.setAccepted(true);
						break;
			default: 	logger.warn("Invalid Commmand: " + command);
						UTuvcs.getStickShift().setSelectedIndex(-1); 
		}
	}
	
	public void trottle(){ // loop the engine sound to keep the engine running until the engine is switched off
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int engineTiming = 0; 
			public void run() {
					engineTiming ++;
				if(UTuvcs.getStateLabel().getText().equals("OFF")) {
					effects.clip.stop();// stop the sound id the engine is switched off
					timer.cancel();
				}else if(engineTiming%2 == 0) {// play the sound every 2 seconds
					soundEffect("trottle");// select the sound and play it	
				}
				if(engineTiming == 10)// control the timing variable to prevent it from reaching a very large number 
					engineTiming = 0;
				
			}	
		}, 0, 1000);
	}
}
