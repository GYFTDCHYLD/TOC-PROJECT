package com.state;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.domain.UTuvcs;
import com.image.loadImages;
import com.sound.Mp3;
import com.sound.SoundEffects;

import javazoom.jl.decoder.JavaLayerException;

public class State {
	protected SoundEffects effects = new SoundEffects();
	protected static Mp3 mp3Player = new Mp3();
	protected final Logger logger = LogManager.getLogger(State.class);
	protected String name; 

	public State(String name) {
		super();
		this.name = name;
	}
	
	public void Control(String command) throws InterruptedException {
		
	}

	
	public void seatbelt(){ // this function is used to create a blinking effect for the seatbelt signal  
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int seatbeltTiming = 0; 
			public void run() {
				seatbeltTiming ++;
				if(UTuvcs.getStateLabel().getText().equals("ENGINE-STARTED")) {
					if(seatbeltTiming%2 == 0) {// show the seat belt every other second
						UTuvcs.getSeatbelt().setVisible(true);
					}else {// hide the seat belt every other second
						UTuvcs.getSeatbelt().setVisible(false);
					}
				}else{
					UTuvcs.getSeatbelt().setVisible(false);
					timer.cancel();	
				}
				if(seatbeltTiming == 10)// control the timing variable to prevent it from reaching a very large number 
					seatbeltTiming = 0;
			}	
		}, 0, 500);
	}
	
	
	public void accelerateDecelerate(String option){// this function is used to control the number added/subtracted from the speed-meter
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 0;
			public void run() {
				if(option.equals("trottle")) { 
					i ++;
					UTuvcs.setSpeed(UTuvcs.getSpeed() + 1);
					UTuvcs.shift();
					UTuvcs.getSpeedometer().setText("" + UTuvcs.getSpeed()); 
					if ((i == 15 && name.equals("IN-FORWARD-MOTION")) || (i == 5 && name.equals("IN-REVERSE-MOTION")) || UTuvcs.getSpeed() >= 220) { 
						timer.cancel();
					}
				}else {
					i --;
					UTuvcs.setSpeed(UTuvcs.getSpeed() - 1);
					UTuvcs.shift(); 
					UTuvcs.getSpeedometer().setText("" + UTuvcs.getSpeed()); 
					if ((i <= -15 && name.equals("IN-FORWARD-MOTION")) || (i <= -5 && name.equals("IN-REVERSE-MOTION")) || UTuvcs.getSpeed() <= 0 ) {
						if(UTuvcs.getSpeed() <= 0) {  
							UTuvcs.vehicleControl("z");
							UTuvcs.setSpeed(0);
						}
						timer.cancel();	
					}
				}
				if(UTuvcs.getSpeed() < 10)
					UTuvcs.getSpeedometer().setBounds(329, 200, 300, 100);
				else if(UTuvcs.getSpeed() > 9 && UTuvcs.getSpeed() < 100)
					UTuvcs.getSpeedometer().setBounds(323, 200, 300, 100);
				else {
					UTuvcs.getSpeedometer().setBounds(318, 200, 300, 100);
				}
			}	
		}, 0, 300);
	}
	
	

	
	public static int selectedSong = 1;// set song to the first song by default
	public static boolean Play = false;// use to check if the song was previously set to stop or play
	public void radio(){// radio function, hidden when car is in reverse
		try {
			if(UTuvcs.getRadio().isVisible()) {
				if(Play)
					State.getMp3Player().getPlayer().close();
				hideRadio(); 
			}else {
				showRadio(); 
				songCover(String.valueOf(selectedSong)); 
				if(Play)
					playSong();	
			}
		}catch(NullPointerException e) {
			UTuvcs.getRadio().setVisible(false);
			UTuvcs.getPrevious().setVisible(false);
			UTuvcs.getPlayStop().setVisible(false);
			UTuvcs.getNext().setVisible(false);
			logger.error("NullPointerException  Caught");
		}
	}
	
	public void hideRadio() {// hide radio
		UTuvcs.getRadio().setVisible(false);
		UTuvcs.getPrevious().setVisible(false);
		UTuvcs.getPlayStop().setVisible(false);
		UTuvcs.getNext().setVisible(false);
	}
	public void showRadio() {// show radio
		UTuvcs.getRadio().setVisible(true);
		UTuvcs.getPrevious().setVisible(true);
		UTuvcs.getPlayStop().setVisible(true);
		UTuvcs.getNext().setVisible(true);
	}
	
	public void songCover(String art){// display the album-art for the selected song 
		switch (art){
			case "1":UTuvcs.getRadio().setIcon(new ImageIcon(loadImages.buju));
				break;
			case "2":UTuvcs.getRadio().setIcon(new ImageIcon(loadImages.roddyRich));
				break;
			default:
		}
	}
	

	
	public void playSong(){// play mp3 file for the radio function
		try {
			if(Play)
				State.getMp3Player().getPlayer().close();
			mp3Player.playMp3(String.valueOf(selectedSong));
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException Caught");
		} catch(JavaLayerException e) {
			logger.error("JavaLayerException Caught");
		}catch(NullPointerException e) {
			logger.error("NullPointerException  Caught");
		}catch(Exception e) {
			logger.error("Unknown Exception  Caught");
		}
	}
	
	public void soundEffect(String choice) {
		try {
			effects.setSound(choice);
			effects.play();/* use the  getSound method from soundEffects class and pass it through the play sound method  effects */
		}catch (LineUnavailableException e) {/* set sound effect by number in soundEffects class */ 
			logger.error("LineUnavailableException Caught");
		} catch (IOException e) {
			logger.error("IOException Caught");
		} catch (UnsupportedAudioFileException e) {
			logger.error("UnsupportedAudioFileException Caught");
		}
	}
	
	public void reject() {
		soundEffect("rejected");
		UTuvcs.getStickShift().setSelectedIndex(-1); 
	}
	
	
	public String getName() {
		return name;
	}

	public Logger getLogger() {
		return logger;
	}

	public static Mp3 getMp3Player() {
		return mp3Player;
	}
	
}
