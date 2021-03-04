package com.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sound.Mp3;
import com.sound.SoundEffects;

import javazoom.jl.decoder.JavaLayerException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;

public class UTuvcs extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SoundEffects effects = new SoundEffects();
	private JLabel ProcessString = new JLabel("");
	private JLabel State = new JLabel("OFF");
	private JLabel Speedometer = new JLabel(""); 
	private JLabel Gear = new JLabel("");// display the gear the car is in on the left clock on the dash
	private JLabel GearIndicatior = new JLabel("*");
	private Icon icon = new ImageIcon("image/start.png");// display a image on the start button
	private JButton START = new JButton(icon);
	private JButton CruiseControl = new JButton("SET-CRUISE-CONTROL");
	private JButton SeatBelt = new JButton("SEAT-BELT-ENGAGED");
	private JButton BRAKE = new JButton("BRAKE");
	private JButton Drive = new JButton("DRIVE");
	private JButton Park = new JButton("PARK");
	private JButton Reverse = new JButton("REVERSE");
	private JButton ACCELERATE = new JButton("ACCELERATE");
	private ArrayList<String> InputArray = new ArrayList<String>(); 
	private JLabel interior = new JLabel(""); 
	private JLabel radio = new JLabel(""); 
	private JLabel reverseCam = new JLabel(""); 
	private JLabel seatbelt = new JLabel("");
	private JLabel input = new JLabel(""); 
	
	private JButton Previous = new JButton("<");// radio button
	private JButton PlayStop = new JButton("play");
	private JButton Next = new JButton(">");
	
	private int Speed = 0;
	private boolean isAccepted = false; 
	private String Alphabet = "abcdhnprsz";
	private String command = " ";
	private final Logger logger = LogManager.getLogger(UTuvcs.class);
	
	public UTuvcs(String name){
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		//setBounds(84, 265, 1000, 700);
		setSize(1000, 700); // width, height
		setTitle(name.toUpperCase());
		setBackground(new Color(55, 50, 80));
		
		
		
		input.setFont(new Font("arial", Font.BOLD, 20));
		input.setBounds(494, 261, 50, 50);
		add(input);
		
		GearIndicatior.setFont(new Font("arial", Font.BOLD, 23));
		GearIndicatior.setForeground(Color.GREEN);
		add(GearIndicatior);
		GearIndicatior.setVisible(false);// dont show this indicator unless the car is in drive or reverse
		
		
		ProcessString.setSize(260, 40); // width, height
		ProcessString.setLocation(2, 470); // x, y
		ProcessString.setHorizontalAlignment(SwingConstants.LEFT);
		ProcessString.setBounds(10, 595, 1000, 100);
		ProcessString.setFont(new Font("arial", Font.ITALIC, 17));
		add(ProcessString);

		
		
		State.setSize(260, 40); // width, height
		State.setLocation(2, 470); // x, y
		State.setHorizontalAlignment(SwingConstants.LEFT);
		State.setBounds(628, 240, 300, 100);
		State.setFont(new Font("arial", Font.ITALIC, 19));
		State.setForeground(Color.DARK_GRAY); 
		add(State);
		
		
		Speedometer.setSize(260, 40); // width, height
		Speedometer.setLocation(2, 470); // x, y
		Speedometer.setHorizontalAlignment(SwingConstants.LEFT);
		Speedometer.setBounds(329, 200, 300, 100);
		Speedometer.setFont(new Font("arial", Font.BOLD, 20));
		Speedometer.setBackground(Color.WHITE);
		Speedometer.setForeground(Color.WHITE);
		add(Speedometer);
		
		
		Gear.setSize(260, 40); // width, height
		Gear.setLocation(2, 470); // x, y
		Gear.setHorizontalAlignment(SwingConstants.LEFT);
		Gear.setBounds(247, 201, 300, 100);
		Gear.setFont(new Font("arial", Font.BOLD, 20));
		Gear.setBackground(Color.WHITE);
		Gear.setForeground(Color.WHITE);
		add(Gear);
		
	   /********************************<<    BUTTONS STARTS    >>********************************/
		START.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command = "s";
				process(command);
			}
		});
		START.setBounds(283, 290, 20, 20);
		add(START);
		
		CruiseControl.addActionListener(this);
		CruiseControl.setBounds(415, 410, 170, 30);
		add(CruiseControl);
		
		SeatBelt.addActionListener(this);
		SeatBelt.setBounds(400, 610, 200, 30);
		add(SeatBelt);
		
		Drive.addActionListener(this);
		Drive.setBounds(500, 440, 80, 30);
		add(Drive);
		
		Park.addActionListener(this);
		Park.setBounds(350, 440, 60, 30);
		add(Park);
		
		Reverse.addActionListener(this);
		Reverse.setBounds(420, 440, 80, 30);
		add(Reverse);
		
		BRAKE.addActionListener(this);
		BRAKE.setBounds(295, 440, 65, 30);
		add(BRAKE);
		
		ACCELERATE.addActionListener(this);
		ACCELERATE.setBounds(195, 440, 110, 30);
		add(ACCELERATE);
		
		
		Previous.addActionListener(this);
		Previous.setBounds(430, 360, 40, 30);
		add(Previous);
		Previous.setVisible(false);
		
		PlayStop.addActionListener(this);
		PlayStop.setBounds(470, 360, 60, 30);
		add(PlayStop);
		PlayStop.setVisible(false);
		
		Next.addActionListener(this);
		Next.setBounds(530, 360, 40, 30);
		add(Next);
		Next.setVisible(false);
		/********************************<<    BUTTONS ENDS    >>********************************/
		
		
		radio.setIcon(new ImageIcon("image/radio.png"));
		radio.setBounds(445, 270, 300, 200);
		add(radio);
		radio.setVisible(false);
		
		reverseCam.setHorizontalAlignment(SwingConstants.CENTER);
		reverseCam.setIcon(new ImageIcon("image/reverseCam.jpg"));
		reverseCam.setBounds(0, 23,1000, 700);
		add(reverseCam);
		reverseCam.setVisible(false); 
		
		seatbelt.setHorizontalAlignment(SwingConstants.CENTER);
		seatbelt.setIcon(new ImageIcon("image/seatbelt.png"));
		seatbelt.setBounds(110, 265,40, 40);
		add(seatbelt);
		seatbelt.setVisible(false);
		
		interior.setHorizontalAlignment(SwingConstants.CENTER);
		interior.setIcon(new ImageIcon("image/interior.jpg"));
		interior.setBounds(0, 0,1000, 700);
		add(interior);	
	}
	
	public void actionPerformed(ActionEvent e) {/** action listener function that execute commands based on the button being pressed **/
		
		switch (e.getActionCommand()){ /** check which button has been pressed **/
			
			case "SET-CRUISE-CONTROL":
					command = "c";
					process(command);
					break;
			case "SEAT-BELT-ENGAGED":
					command = "b";
					process(command);
					break;
			case "DRIVE":
					command = "d";
					process(command);
					break;
			case "PARK":
					command = "n";
					process(command);
					break;
			case "REVERSE":
					command = "r";
					process(command);
					break;
			case "BRAKE":
					if(JOptionPane.showConfirmDialog(null, "HOLD-BRAKE?", "TOC", 0) == 0) {
						command = "h";
						process(command);
						Speedometer.setBounds(329, 200, 300, 100);
					}else{
						command = "p";  
						process(command);
					}
					break;
			case "ACCELERATE":
					command = "a";  
					process(command);
					break;
			case "<":
					if(selectedSong > 1)
						selectedSong--;
					else
						selectedSong = 2;
					stopSong();
					songCover(String.valueOf(selectedSong)); 
					if(Play) {
						PlayStop.setText("stop");
						playSong();
					}
					break;
			case "play":
						radio.setVisible(true);
						playSong();
						PlayStop.setText("stop");
						Play = true;
					break;
			case "stop":
					stopSong();
					PlayStop.setText("play");
					Play = false;
					break;
			case">":
					if(selectedSong < 2)
						selectedSong++;
					else
						selectedSong = 1;
					stopSong();
					songCover(String.valueOf(selectedSong));
					if(Play) {
						PlayStop.setText("stop");
						playSong();
					}
					break;
			default: 
					logger.error("Action not Recognized");
		}
	} 

	public void process(String input) {/** this function checks if the input is a part of the accepted alphabet **/
		isAccepted = false;// set accept to false by default 
		if (isApartOfTheLanguage(input))
			vehicleControl(input);
		else 
			logger.warn( "\""+ input + "\" is not a recognized/acceptable Input");
		
	}
	
	public void vehicleControl(String command) { // this function controls the states transitions
		
		try {
			
			if(State.getText().equals("IN-FORWARD-MOTION")) {
				
				switch (command){  
					case "a":	InputArray.add("a");  
								accelerateDecelerate("trottle");
								effects.clip.stop();
								isAccepted = true;
								soundEffect("gas2");
								break;
					case "c":	InputArray.add("c"); 
								State.setText("CRUISE-CONTROL-ENGAGED");
								isAccepted = true;
								break; 
					case "p":	InputArray.add("p");
								accelerateDecelerate("brake");
								isAccepted = true;
								break;
					case "z":	InputArray.add("z");
								State.setText("STATIONARY-POSITION");
								GearIndicatior.setVisible(false);
								isAccepted = true;
								break; 
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			} else if(State.getText().equals("IN-REVERSE-MOTION")) {
				
				switch (command){  
					case "a": 	InputArray.add("a");  
								accelerateDecelerate("trottle");
								isAccepted = true;
								soundEffect("gas");
								break;
					case "p":	InputArray.add("p");
								accelerateDecelerate("brake");
								isAccepted = true;
								break; 
					case "h":	InputArray.add("h");
								Speed = 0;
								State.setText("STATIONARY-POSITION");
								reverseCam.setVisible(false);
								GearIndicatior.setVisible(false);
								radio();
								isAccepted = true;
								break;
					case "z":	InputArray.add("z");
								State.setText("STATIONARY-POSITION");
								reverseCam.setVisible(false);
								GearIndicatior.setVisible(false);
								radio();
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("CRUISE-CONTROL-ENGAGED")) {
				
				switch (command){  
					case "a": 	InputArray.add("a");
								isAccepted = true;
								break;
					case "p":	InputArray.add("p");
								State.setText("IN-FORWARD-MOTION");
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
						 
				}
				
			}else if(State.getText().equals("STATIONARY-POSITION")) {
				
				switch (command){  
					case "a": 	InputArray.add("a");
								isAccepted = true;
								soundEffect("gas");
								break;
					case "d":	InputArray.add("d");
								Speed ++;
								State.setText("IN-FORWARD-MOTION");
								Gear.setText("1");
								GearIndicatior.setBounds(524, 523, 20, 20);// green indicator to the right of the gear shift/stick
								GearIndicatior.setVisible(true);
								isAccepted = true;
								break;
					case "r":	InputArray.add("r");
								Speed ++;
								State.setText("IN-REVERSE-MOTION");
								reverseCam.setVisible(true);
								Gear.setText("R");
								GearIndicatior.setBounds(524, 541, 20, 20);
								GearIndicatior.setVisible(true);
								radio();
								isAccepted = true;
								break;
					case "n":	InputArray.add("n");
								State.setText("ENGINE-STARTED");
								seatbelt();
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("ENGINE-STARTED")) {
				
				switch (command){   
					case "s": 	InputArray.add("s");
								State.setText("OFF");
								Speedometer.setText(""); 
								soundEffect("stopEngine");
								State.setForeground(Color.DARK_GRAY); 
								radio();
								seatbelt.setVisible(false);
								isAccepted = true;
								logger.info(InputArray.toString().replaceAll(", ", ""));// save the string to a log file
								InputArray.clear();
								ProcessString.setVisible(false);
								break;
					case "b":	InputArray.add("b");
								State.setText("STATIONARY-POSITION");
								seatbelt.setVisible(false);
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("IGNITION-ON")) {
				
				switch (command){   
					case "s": 	InputArray.add("s");
								State.setText("OFF");
								Speedometer.setText(""); 
								State.setForeground(Color.DARK_GRAY);
								radio();
								isAccepted = true;
								logger.info(InputArray.toString().replaceAll(", ", ""));// save the string to a log file
								InputArray.clear();
								ProcessString.setVisible(false); 
								break;
					case "h":	InputArray.add("h");
								State.setText("HOLD");
								radio();
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("HOLD")) {
				
				switch (command){  
					case "s": 	InputArray.add("s"); 
								State.setText("ENGINE-STARTED");
								soundEffect("startEngine");
								State.setForeground(Color.WHITE);
								radio();
								trottle();
								seatbelt();
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
	
			}else {
				
				switch (command){  
					case "s": 	InputArray.add("s");
								State.setText("IGNITION-ON");
								soundEffect("ignition");
								State.setForeground(Color.WHITE);
								radio();
								isAccepted = true;
								ProcessString.setVisible(true);
								break;
					case "h":	InputArray.add("h");
								State.setText("HOLD");
								State.setForeground(Color.WHITE);
								isAccepted = true; 
								ProcessString.setVisible(true);
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}
			
			if(!(State.getText().equals("OFF") || State.getText().equals("HOLD")))
				Speedometer.setText("" + Speed);
			
			input.setText(command);// display the current input signal, if input is accepted the color of the input is green and if the input is not accepted then the color turns red  
			if(isAccepted)
				input.setForeground(Color.GREEN);
			else 
				input.setForeground(Color.RED);
			
			ProcessString.setText(InputArray.toString());
			shift();
		}catch (InterruptedException e) {
			logger.error("InterruptedException Caught");
		}
	}
	
	public boolean isApartOfTheLanguage(String letter){
	    for(int i = 0; i < Alphabet.length(); i++) { // iterate to the length of the alphabet
	        if( Alphabet.charAt(i) == letter.charAt(0))// check the character at the index of the alphabet with the given letter
	            return true;// return true if the input is a part of the language
	    }
		return false;// return false if the input is not a part of the language
	}
	
	
	public void accelerateDecelerate(String option){// this function is used to control the number added/subtracted from the speed-meter
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 0;
			public void run() {
				if(option.equals("trottle")) { 
					i ++;
					Speed ++;
					shift();
					Speedometer.setText("" + Speed); 
					if ((i == 15 && State.getText().equals("IN-FORWARD-MOTION")) || (i == 5 && State.getText().equals("IN-REVERSE-MOTION")) || Speed >= 220) { 
						timer.cancel();
					}
				}else {
					i --;
					Speed --;
					shift(); 
					Speedometer.setText("" + Speed); 
					if ((i <= -15 && State.getText().equals("IN-FORWARD-MOTION")) || (i <= -5 && State.getText().equals("IN-REVERSE-MOTION")) || Speed <= 0 ) {
						if(Speed <= 0) {  
							vehicleControl("z");
							Speed = 0;
						}
						timer.cancel();	
					}
				}
				if(Speed < 10)
					Speedometer.setBounds(329, 200, 300, 100);
				else if(Speed > 9 && Speed < 100)
					Speedometer.setBounds(323, 200, 300, 100);
				else {
					Speedometer.setBounds(318, 200, 300, 100);
				}
			}	
		}, 0, 300);
	}
	
	
	public void seatbelt(){ // this function is used to create a blinking effect for the seatbelt signal  
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int seatbeltTiming = 0; 
			public void run() {
				seatbeltTiming ++;
				if(State.getText().equals("ENGINE-STARTED")) {
					if(seatbeltTiming%2 == 0) {// show the seat belt every other second
						seatbelt.setVisible(true);
					}else {// hide the seat belt every other second
						seatbelt.setVisible(false);
					}
				}else{
					seatbelt.setVisible(false);
					timer.cancel();	
				}
				if(seatbeltTiming == 10)// control the timing variable to prevent it from reaching a very large number 
					seatbeltTiming = 0;
			}	
		}, 0, 500);
	}
	
	
	public void trottle(){ // loop the engine sound to keep the engine running until the engine is switched off
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int engineTiming = 0; 
			public void run() {
					engineTiming ++;
				if(State.getText().equals("OFF")) {
					effects.clip.stop();// stop the sound id the engine is switched off
					timer.cancel();
				}else if(engineTiming%2 == 0) {// play the sound every 2 seconds
					try {
						soundEffect("trottle");// select the sound and play it
					} catch (InterruptedException e) {
						logger.error("InterruptedException Caught");
					}	
				}
				if(engineTiming == 10)// control the timing variable to prevent it from reaching a very large number 
					engineTiming = 0;
				
			}	
		}, 0, 1000);
	}

	
	public void shift() { // controls the speed-text in the right clock on the dash and the gear-text in the left clock  
		if(State.getText().equals("IN-FORWARD-MOTION") || State.getText().equals("CRUISE-CONTROL-ENGAGED")) {
			if(Speed > 0 && Speed <= 27)
				Gear.setText("1");
			else if(Speed > 27 && Speed <= 54)
				Gear.setText("2");
			else if(Speed > 54 && Speed <= 81)
				Gear.setText("3");
			else if(Speed > 81 && Speed <= 108)
				Gear.setText("4");
			else if(Speed > 108 && Speed <= 135)
				Gear.setText("5");
			else if(Speed > 135 && Speed <= 162)
				Gear.setText("6");
			else if(Speed > 162 && Speed <= 189)
				Gear.setText("7");
			else if(Speed > 189 && Speed <= 220)
				Gear.setText("8");
			else if(Speed > 220)// allows the car speed to max at 220 in forward motion 
				Speed = 220;
		}else if(State.getText().equals("IN-REVERSE-MOTION")) {
			Gear.setText("R");
			if(Speed >= 30)// allows the car speed to max at 30 in reverse motion
				Speed = 30;
		}else {
			Gear.setText("");
		}
	}
	
	
	Mp3 mp3Player = new Mp3();
	int selectedSong = 1;
	boolean Play = false;// use to check if the song was previously set to stop or play
	public void radio(){// radio function, hidden when car is in reverse
		stopSong();
		if(radio.isVisible()) {
			radio.setVisible(false);
			Previous.setVisible(false);
			PlayStop.setVisible(false);
			Next.setVisible(false);
		}else {
			radio.setVisible(true);
			Previous.setVisible(true);
			PlayStop.setVisible(true);
			Next.setVisible(true);
			songCover(String.valueOf(selectedSong)); 
			if(Play)
				playSong();
		}
	}
	
	public void songCover(String art){// display the album-art for the selected song 
		switch (art){
			case "1":radio.setIcon(new ImageIcon("image/Radio/1.png"));
				break;
			case "2":radio.setIcon(new ImageIcon("image/Radio/2.png"));
				break;
			default:
		}
	}
	
	@SuppressWarnings("deprecation")
	public void stopSong() {// stop mp3 file for the radio function
		mp3Player.songThread.stop();
		mp3Player = new Mp3();
	}
	
	public void playSong() {// play mp3 file for the radio function
		try {
			mp3Player.playMp3(String.valueOf(selectedSong));
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException Caught");
		} catch(JavaLayerException e) {
			logger.error("JavaLayerException Caught");
		}catch(Exception e) {
			logger.error("Unknown Exception  Caught");
		}
	}
	
	
	public void soundEffect(String choice) throws InterruptedException {
		try {
			effects.setSound(choice);
			effects.PlaySound(effects.getSound());/* use the  getSound method from soundEffects class and pass it through the play sound method  effects */
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException Caught");
		}catch (LineUnavailableException e) {/* set sound effect by number in soundEffects class */ 
			logger.error("LineUnavailableException Caught");
		} catch (IOException e) {
			logger.error("IOException Caught");
		} catch (UnsupportedAudioFileException e) {
			logger.error("UnsupportedAudioFileException Caught");
		}catch(Exception e) {
			logger.error("Unknown Exception  Caught");
		}
	}
	
	public boolean getAcceptStatus() {
		return isAccepted;
	}

}
