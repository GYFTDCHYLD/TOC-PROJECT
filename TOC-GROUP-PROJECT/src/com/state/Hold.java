package com.state;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import com.domain.UTuvcs;
import com.image.loadImages;

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
						reject();
						UTuvcs.getStickShift().setSelectedIndex(-1); 
		}
	}
	
	public void trottle(){ // loop the engine sound to keep the engine running until the engine is switched off
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int engineTiming = 0; 
			public void run() {
					animateAssets();
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
	
	public void animateAssets(){ // controls the spinning of the wheel and the sliding of assets in background
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int spin = 0, slide = 0;
			public void run() {
				if(UTuvcs.getSpeed() != 0) {
					UTuvcs.getCarRearWheel().setIcon(new ImageIcon(loadImages.rotateCw(loadImages.carWheel, spin)));
					UTuvcs.getCarFrontWheel().setIcon(new ImageIcon(loadImages.rotateCw(loadImages.carWheel, spin)));
					
					if((UTuvcs.getTree().getBounds().x-slide)  < (-700)) {
						UTuvcs. getTree().setIcon(new ImageIcon(randomTree(ran)));
						UTuvcs. getTree().setBounds(1000, -35, UTuvcs.getTree().getWidth(), UTuvcs.getTree().getHeight());
					}else if((UTuvcs.getTree().getBounds().x+slide) > 1400){
						UTuvcs. getTree().setIcon(new ImageIcon(randomTree(ran)));
						UTuvcs. getTree().setBounds(-300, -35, UTuvcs.getTree().getWidth(), UTuvcs.getTree().getHeight());
					}
					UTuvcs. getTree().setBounds(UTuvcs.getTree().getBounds().x-slide, -35, UTuvcs.getTree().getWidth(), UTuvcs.getTree().getHeight());
					if(UTuvcs.getStateLabel().getText().equals("IN-FORWARD-MOTION")) {
						slide = (0 + UTuvcs.getSpeed()) ;
						spin ++;
					}else {
						slide = (0 - UTuvcs.getSpeed());
						spin--;
					}
				}else {
					timer.cancel();
				}
			}	
		}, 0, 300);
	}
	
	static int ran = 0;
	public static BufferedImage randomTree(int key) {
		BufferedImage image = loadImages.mountain2;
		switch (key) {
			case 1:
					image = loadImages.tree1;
				break;
			case 2:
					image = loadImages.rock1;
				break;
			case 3:
					image = loadImages.tree2;
				break;
			case 4:
					image = loadImages.rock2;
				break;
			case 5:
					image = loadImages.tree3;
					break;
			case 6:
					image = loadImages.mountain1;
					break;
			case 7:
					image = loadImages.tree4;
					break;
			case 8:
					image = loadImages.rock3;
					break;
			case 9:
					image = loadImages.mountain2;
					break;

			default:
					break;
		}
		if(ran == 9)
			ran = 1;
		else
			ran ++;
		return image;
	}
}
