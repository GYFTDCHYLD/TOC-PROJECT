package com.sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3 {
	private String name;
	
	public Mp3() {
		
	}
	
	public Mp3(String name) {
		this.name = name;
	}

	BufferedInputStream BufferedInputStream;
	FileInputStream FileInputStream;
	Player player;
	
	public void playMp3(String name) throws FileNotFoundException, JavaLayerException{  
		setSound(name);
		player = new Player(BufferedInputStream);
		songThread.start();
	}
	
	
	public void setSound(String option) throws FileNotFoundException{
		switch (option){
			case "1": FileInputStream = new FileInputStream("soundEffects/buju.mp3");
						 BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "2": FileInputStream = new FileInputStream("soundEffects/Roddy Ricch.mp3");
			 			 BufferedInputStream = new BufferedInputStream(FileInputStream);
			 break;
			default:
		}
	}
	
	public Thread songThread = new Thread() { 
		public void run() {
			try {
				player.play();
			} catch (JavaLayerException e) {
					e.printStackTrace();
			}
		}		
	};

}
