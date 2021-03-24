package com.sound;


import java.io.*;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundEffects {
	public Clip clip;
	private loadSounds loadSounds = new loadSounds();
	
	public SoundEffects() {
		loadSounds.init();
	}
	
	public void setSound(String option) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
	
		switch (option){
			case "ignition": 	PlaySound(loadSounds.ignition); 
							break;
			case "startEngine": PlaySound(loadSounds.startEngine);
							break;
			case "stopEngine": 	PlaySound(loadSounds.stopEngine);
							break;
			case "gas": 		PlaySound(loadSounds.gas);
							break;
			case "gas2": 		PlaySound(loadSounds.gas2);
							break;
			case "trottle": 	PlaySound(loadSounds.trottle);
							break;
			default: 
								PlaySound(loadSounds.rejected);
		}
		
	}

	public void PlaySound(URL filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream sound = AudioSystem.getAudioInputStream(filename); 
		clip = AudioSystem.getClip();
		clip.open(sound);
	}


	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		clip.stop();
		clip.close();
	}
}
