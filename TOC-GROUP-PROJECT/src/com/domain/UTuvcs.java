package com.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.keyPress.KeyPress;
import com.sound.Mp3;
import com.sound.SoundEffects;

import javazoom.jl.decoder.JavaLayerException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;

public class UTuvcs extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SoundEffects effects = new SoundEffects();
	private static JLabel ProcessString = new JLabel("");
	private static JLabel State = new JLabel("OFF");
	private static JLabel Speedometer = new JLabel(""); 
	private static JLabel Gear = new JLabel("");
	private static Icon icon = new ImageIcon("image/start.png"); 
	private static JButton START = new JButton(icon);
	private static JButton CruiseControl = new JButton("SET-CRUISE-CONTROL");
	private static JButton SeatBelt = new JButton("SEAT-BELT-ENGAGED");
	private static JButton BRAKE = new JButton("BRAKE");
	private static JButton Drive = new JButton("Drive");
	private static JButton Park = new JButton("PARK");
	private static JButton Reverse = new JButton("REVERSE");
	private static JButton ACCELERATE = new JButton("ACCELERATE");
	private static Stack<String> InputStack = new Stack<String>();
	private static JLabel interior = new JLabel(""); 
	private static JLabel radio = new JLabel(""); 
	private static JLabel reverseCam = new JLabel(""); 
	private static JLabel seatbelt = new JLabel("");
	private static JLabel input = new JLabel(""); 
	
	private static JButton Previous = new JButton("<");// radio button
	private static JButton PlayStop = new JButton("play");
	private static JButton Next = new JButton(">");
	
	
	private static int Speed = 0;
	private static boolean isAccepted = false; 
	private String Alphabet = "abcdhnprsz";
	private String command = " ";
	private static final Logger logger = LogManager.getLogger(UTuvcs.class);
	
	public UTuvcs(String name){
		addKeyListener(KeyPress.keyAdapter);
		
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
		
	
		START.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command = "s";
				process(command);
			}
		});
		START.setBounds(283, 290, 20, 20);
		add(START);
		
		CruiseControl.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				command = "c";
				process(command);
			}
		});
		CruiseControl.setBounds(415, 410, 170, 30);
		add(CruiseControl);
		
		SeatBelt.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				command = "b";
				process(command);
			}
		});
		SeatBelt.setBounds(400, 610, 200, 30);
		add(SeatBelt);
		
		Drive.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				command = "d";
				process(command);
			}
		});
		Drive.setBounds(500, 440, 80, 30);
		add(Drive);
		
		Park.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				command = "n";
				process(command);
			}
		});
		Park.setBounds(350, 440, 60, 30);
		add(Park);
		
		Reverse.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				command = "r";
				process(command);
			}
		});
		Reverse.setBounds(420, 440, 80, 30);
		add(Reverse);
		
		BRAKE.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
			
				if(JOptionPane.showConfirmDialog(null, "HOLD-BRAKE?", "TOC", 0) == 0) {
					command = "h";
					process(command);
					Speedometer.setBounds(329, 200, 300, 100);
				}else{
					command = "p";  
					process(command);
				}
			}
		});
		BRAKE.setBounds(295, 440, 65, 30);
		add(BRAKE);
		
		ACCELERATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command = "a";  
				process(command);
			}
		});
		ACCELERATE.setBounds(195, 440, 110, 30);
		add(ACCELERATE);
		
		
		Previous.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		Previous.setBounds(430, 360, 40, 30);
		add(Previous);
		Previous.setVisible(false);
		
		PlayStop.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(PlayStop.getText().equals("stop")) {
					stopSong();
					PlayStop.setText("play");
					Play = false;
				}else { 
					radio.setVisible(true);
					playSong();
					PlayStop.setText("stop");
					Play = true;
				}
			}
		});
		PlayStop.setBounds(470, 360, 60, 30);
		add(PlayStop);
		PlayStop.setVisible(false);
		
		Next.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		Next.setBounds(530, 360, 40, 30);
		add(Next);
		Next.setVisible(false);
		
		
		
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

	public void process(String input) {
		if (isApartOfTheLanguage(input))
			vehicleControl(input);
	}
	
	public void vehicleControl(String command) {  
		
		try {
			
			if(State.getText().equals("IN-FORWARD-MOTION")) {
				
				switch (command){  
					case "a":	InputStack.push("a"); 
								accelerateDecelerate("trottle");
								effects.clip.stop();
								isAccepted = true;
								soundEffect("gas2");
								break;
					case "c":	InputStack.push("c"); 
								State.setText("CRUISE-CONTROL-ENGAGED");
								isAccepted = true;
								break; 
					case "p":	InputStack.push("p");
								accelerateDecelerate("brake");
								isAccepted = true;
								break;
					case "z":	InputStack.push("z");
								State.setText("STATIONARY-POSITION");
								isAccepted = true;
								break; 
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			} else if(State.getText().equals("IN-REVERSE-MOTION")) {
				
				switch (command){  
					case "a": 	InputStack.push("a");  
								accelerateDecelerate("trottle");
								isAccepted = true;
								soundEffect("gas");
								break;
					case "p":	InputStack.push("p");
								accelerateDecelerate("brake");
								isAccepted = true;
								break; 
					case "h":	InputStack.push("h");
								Speed = 0;
								State.setText("STATIONARY-POSITION");
								reverseCam.setVisible(false);
								radio();
								isAccepted = true;
								break;
					case "z":	InputStack.push("z");
								State.setText("STATIONARY-POSITION");
								reverseCam.setVisible(false); 
								radio();
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("CRUISE-CONTROL-ENGAGED")) {
				
				switch (command){  
					case "a": 	InputStack.push("a");
								isAccepted = true;
								break;
					case "p":	InputStack.push("p");
								State.setText("IN-FORWARD-MOTION");
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
						 
				}
				
			}else if(State.getText().equals("STATIONARY-POSITION")) {
				
				switch (command){  
					case "a": 	InputStack.push("a");
								isAccepted = true;
								soundEffect("gas");
								break;
					case "d":	InputStack.push("d");
								Speed ++;
								State.setText("IN-FORWARD-MOTION");
								Gear.setText("1");
								isAccepted = true;
								break;
					case "r":	InputStack.push("r");
								Speed ++;
								State.setText("IN-REVERSE-MOTION");
								reverseCam.setVisible(true);
								Gear.setText("R");
								radio();
								isAccepted = true;
								break;
					case "n":	InputStack.push("n");
								State.setText("ENGINE-STARTED");
								seatbelt();
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("ENGINE-STARTED")) {
				
				switch (command){   
					case "s": 	InputStack.push("s");
								State.setText("OFF");
								Speedometer.setText(""); 
								soundEffect("stopEngine");
								State.setForeground(Color.DARK_GRAY); 
								radio();
								seatbelt.setVisible(false);
								isAccepted = true;
								logger.info(InputStack.toString().replaceAll(", ", ""));// save the string to a log file
								InputStack.clear();
								ProcessString.setVisible(false);
								break;
					case "b":	InputStack.push("b");
								State.setText("STATIONARY-POSITION");
								seatbelt.setVisible(false);
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("IGNITION-ON")) {
				
				switch (command){   
					case "s": 	InputStack.push("s");
								State.setText("OFF");
								Speedometer.setText(""); 
								State.setForeground(Color.DARK_GRAY);
								radio();
								isAccepted = true;
								logger.info(InputStack.toString().replaceAll(", ", ""));// save the string to a log file
								InputStack.clear();
								ProcessString.setVisible(false); 
								break;
					case "h":	InputStack.push("h");
								State.setText("HOLD");
								radio();
								isAccepted = true;
								break;
					default: 	logger.warn("Invalid Commmand: " + command);
				}
				
			}else if(State.getText().equals("HOLD")) {
				
				switch (command){  
					case "s": 	InputStack.push("s"); 
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
					case "s": 	InputStack.push("s");
								State.setText("IGNITION-ON");
								soundEffect("ignition");
								State.setForeground(Color.WHITE);
								radio();
								isAccepted = true;
								ProcessString.setVisible(true);
								break;
					case "h":	InputStack.push("h");
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
			if(isAccepted) { 
				input.setForeground(Color.GREEN);
				isAccepted = false;
			}else 
				input.setForeground(Color.RED);
			
			ProcessString.setText(InputStack.toString());
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
	
	
	public void accelerateDecelerate(String option){
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
	
	
	public void seatbelt(){   
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int seatbeltTiming = 0; 
			public void run() {
				seatbeltTiming ++;
				if(State.getText().equals("ENGINE-STARTED")) {
					if(seatbeltTiming%2 == 0) {
						seatbelt.setVisible(true);
					}else {
						seatbelt.setVisible(false);
					}
				}else{
					seatbelt.setVisible(false);
					timer.cancel();	
				}
				if(seatbeltTiming == 10)
					seatbeltTiming = 0;
			}	
		}, 0, 500);
	}
	
	
	public void trottle(){  
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int engineTiming = 0; 
			public void run() {
					engineTiming ++;
				if(State.getText().equals("OFF")) {
					effects.clip.stop();
					timer.cancel();
				}else if(engineTiming%2 == 0) {
					try {
						soundEffect("trottle");	
					} catch (InterruptedException e) {
						logger.error("InterruptedException Caught");
					}	
				}
				if(engineTiming == 10)
					engineTiming = 0;
				
			}	
		}, 0, 1000);
	}

	
	public void shift() { 
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
			else if(Speed > 220)
				Speed = 220;
		}else if(State.getText().equals("IN-REVERSE-MOTION")) {
			Gear.setText("R");
			if(Speed >= 30)
				Speed = 30;
		}else {
			Gear.setText("");
		}
	}
	
	
	Mp3 mp3Player = new Mp3();
	int selectedSong = 1;
	boolean Play = false;
	public void radio(){
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
	
	public void songCover(String art){
		switch (art){
			case "1":radio.setIcon(new ImageIcon("image/Radio/1.png"));
				break;
			case "2":radio.setIcon(new ImageIcon("image/Radio/2.png"));
				break;
			default:
		}
	}
	
	public void stopSong() {
		mp3Player.songThread.stop();
		mp3Player = new Mp3();
	}
	
	public void playSong() {
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

}
