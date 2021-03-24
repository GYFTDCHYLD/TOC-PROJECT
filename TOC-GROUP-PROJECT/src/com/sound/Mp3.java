package com.sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3{
	@SuppressWarnings("unused")
	private String name;
	private Thread Song = new Thread();
	private FileInputStream FileInputStream;
	private BufferedInputStream BufferedInputStream;
	private Player player;
	
	public Mp3() {
		name = "";
	}

	public void playMp3(String name) throws FileNotFoundException, JavaLayerException{  
		try {
			setSound(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		player = new Player(BufferedInputStream);
		Song = new Thread(songThread);
		Song.start(); 
	}
	
	/*
	public void setSound(String option) throws IOException{
		option = select(option);// input from song comes in here as number and are converted to filename in order to find the file by name
		switch (option){
			case "Buju Banton": FileInputStream = new FileInputStream(UTuvcs.loadSounds.BujuBanton);
						 BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "Roddy Ricch": FileInputStream = new FileInputStream(UTuvcs.loadSounds.RoddyRicch);
			 			 BufferedInputStream = new BufferedInputStream(FileInputStream);
			 	break;
			default:
		}
	}
	*/
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
	
	
	public String select(String name) {
		if(name.equals("1")) 
			return "Buju Banton";
		else if(name.equals("2")) 
			return "Roddy Ricch";
		else {
			return name;
		}
	}
	
	
	public Player getPlayer() {
		return player;
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
