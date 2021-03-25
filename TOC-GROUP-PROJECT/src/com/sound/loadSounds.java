package com.sound;

import java.net.URL;

public class loadSounds {
	
	public static  URL ignition; 
	public static  URL startEngine;
	public static  URL stopEngine;
	public static  URL gas;
	public static  URL gas2;
	public static  URL trottle;
	public static  URL rejected;
	public static  URL notRecognized;
	
	public static  URL BujuBanton;
	public static  URL RoddyRicch;
	
	
	public void init() {
		ignition = soundLoader("/ignition.wav");
		startEngine = soundLoader("/startEngine.wav");
		stopEngine = soundLoader("/stopEngine.wav");
		gas = soundLoader("/gas.wav");
		gas2 = soundLoader("/gas2.wav");
		trottle = soundLoader("/trottle.wav");
		rejected = soundLoader("/inputRejected.wav");
		notRecognized = soundLoader("/inputNotRecognized.wav");
	}
	
	
	private URL soundLoader(String path){
		URL resource =  getClass().getResource(path);
		return resource;
	}
}
