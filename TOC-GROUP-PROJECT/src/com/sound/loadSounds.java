package com.sound;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class loadSounds {
	
	public static  File ignition; 
	public static  File startEngine;
	public static  File stopEngine;
	public static  File gas;
	public static  File gas2;
	public static  File trottle;
	public static  File rejected;
	
	public static  File BujuBanton;
	public static  File RoddyRicch;
	
	
	public void init() {
		try {
			ignition = soundLoader("ignition.wav");
			startEngine = soundLoader("startEngine.wav");
			stopEngine = soundLoader("stopEngine.wav");
			gas = soundLoader("gas.wav");
			gas2 = soundLoader("gas2.wav");
			trottle = soundLoader("trottle.wav");
			rejected = soundLoader("input rejected.wav");
			
			BujuBanton = soundLoader("buju.mp3");
			RoddyRicch = soundLoader("Roddy Ricch.mp3");
		} catch (IOException e) {
			
		}
	}
	
	private File soundLoader(String path) throws IOException{
		 ClassLoader classLoader = this.getClass().getClassLoader();
		 URL resource = classLoader.getResource(path);

		 if (resource == null) {
			 throw new IllegalArgumentException("file is not found!");
		 } else {
			 return new File(resource.getFile());
		 }
	 }
	
}
