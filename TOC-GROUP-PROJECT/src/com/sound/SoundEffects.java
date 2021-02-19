package com.sound;


import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {
	public Clip clip;
	private File sound;
	String path;
	
	public void setSound(String option) throws FileNotFoundException{
		switch (option){
			case "ignition": sound = new File("soundEffects/ignition.wav");
				break;
			case "hold": sound = new File("soundEffects/hold.wav");
				break;
			case "startEngine": sound = new File("soundEffects/startEngine.wav");
				break;
			case "stopEngine": sound = new File("soundEffects/stopEngine.wav");
				break;
			case "gas": sound = new File("soundEffects/gas.wav");
				break;
			case "gas2": sound = new File("soundEffects/gas2.wav");
				break;
			case "trottle": sound = new File("soundEffects/trottle.wav");
				break;
			case "stationary": sound = new File("soundEffects/stationaty.wav");
				break;
			case "forward": sound = new File("soundEffects/forward.wav");
				break;
			case "reverse": sound = new File("soundEffects/reverse.wav");
				break;
			case "cruiseEnable": sound = new File("soundEffects/cruiseEnable.wav");
				break;
			case "cruiseDisable": sound = new File("soundEffects/cruiseDisable.wav");
				break;
			case "seatbelt": sound = new File("soundEffects/seatbelt.wav");
				break; 
			case "alarmOff": sound = new File("soundEffects/alarmOff.wav");
				break;
			default: 
				sound = new File("soundEffects/alarmOn.wav");
		}
	}


	public void PlaySound(File sound) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(sound)); 
		clip.start();
	}


	public File getSound() {
		return sound;
	}

}
