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
