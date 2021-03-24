package com.sound;


import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.domain.UTuvcs;


public class SoundEffects {
	public Clip clip;
	private File sound;
	String path;
/*
	
	public void setSound(String option) throws FileNotFoundException{
	
		switch (option){
			case "ignition": 	sound = UTuvcs.loadSounds.ignition; 
							break;
			case "startEngine": sound = UTuvcs.loadSounds.startEngine;
							break;
			case "stopEngine": 	sound = UTuvcs.loadSounds.stopEngine;
							break;
			case "gas": 		sound = UTuvcs.loadSounds.gas;
							break;
			case "gas2": 		sound = UTuvcs.loadSounds.gas2;
							break;
			case "trottle": 	sound = UTuvcs.loadSounds.trottle;
							break;
			default: 
								sound = UTuvcs.loadSounds.rejected;
		}
		
	}
	*/
	
	public void setSound(String option) throws FileNotFoundException{
		switch (option){
			case "ignition": 	sound = new File("soundEffects/ignition.wav");
						break;
			case "startEngine": sound = new File("soundEffects/startEngine.wav");
						break;
			case "stopEngine": 	sound = new File("soundEffects/stopEngine.wav");
						break;
			case "gas": 		sound = new File("soundEffects/gas.wav");
						break;
			case "gas2": 		sound = new File("soundEffects/gas2.wav");
						break;
			case "trottle": 	sound = new File("soundEffects/trottle.wav");
						break;
			case "rejected": 	sound = new File("soundEffects/input rejected.wav");
						break;
			default: 
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
