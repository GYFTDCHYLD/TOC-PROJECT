package com.domain;
import com.state.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Icon;

public class UTuvcs extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel ProcessString = new JLabel("");
	private static JLabel StateLabel = new JLabel("OFF"); 
	private static State State = new Off();// set the state to off state on startup/by default
	private static JLabel Speedometer = new JLabel(""); 
	private static JLabel Gear = new JLabel("");// display the gear the car is in on the left clock on the dash
	private static JLabel GearIndicatior = new JLabel("*");
	private Icon icon = new ImageIcon("image/start.png");// display a image on the start button
	private JButton START = new JButton(icon);
	private JButton SeatBelt = new JButton("SEAT-BELT-ENGAGED");
	private JButton BRAKE = new JButton("BRAKE");
	private JButton ACCELERATE = new JButton("ACCELERATE");
	private static ArrayList<String> InputArray = new ArrayList<String>(); 
	private JLabel interior = new JLabel(""); 
	private static JLabel radio = new JLabel(""); 
	private static JLabel reverseCam = new JLabel(""); 
	private static JLabel seatbelt = new JLabel("");
	private static JLabel input = new JLabel(""); 
	
	private static String[] choices = new String[]{"SET-CRUISE-CONTROL", "DRIVE", "PARK", "REVERSE"};
	private static JComboBox<String> stickShift = new JComboBox<String>(choices);
	
	private static JButton Previous = new JButton("<");// radio button
	private static JButton PlayStop = new JButton("play");
	private static JButton Next = new JButton(">");
	
	private static int Speed = 0;
	private static boolean isAccepted = false; 
	private String Alphabet = "abcdhnprsz";
	private String command = " ";
	
	public UTuvcs(String name){
		addKeyListener(this);
		setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setSize(1000, 700); // width, height
		setTitle(name.toUpperCase());
		getContentPane().setBackground(new Color(102, 153, 255));
		
		
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

		
		StateLabel.setSize(260, 40); // width, height
		StateLabel.setLocation(2, 470); // x, y
		StateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		StateLabel.setBounds(628, 240, 300, 100);
		StateLabel.setFont(new Font("arial", Font.ITALIC, 19));
		StateLabel.setForeground(Color.DARK_GRAY); 
		add(StateLabel);
		
		
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
		
		stickShift.setSelectedIndex(-1);
		stickShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Input(stickShift.getSelectedItem().toString());
				}catch(NullPointerException err) {
					
				}
			}
		});
		stickShift.setBounds(475, 430, 50, 50);
		add(stickShift);
		
		
		SeatBelt.addActionListener(this);
		SeatBelt.setBounds(400, 610, 200, 30);
		add(SeatBelt);
		
		BRAKE.addActionListener(this);
		BRAKE.setBounds(295, 440, 65, 30);
		add(BRAKE);
		
		ACCELERATE.addActionListener(this);
		ACCELERATE.setBounds(195, 440, 110, 30);
		add(ACCELERATE);
		
		
		Previous.addActionListener(this);
		Previous.setBounds(430, 400, 40, 30);
		add(Previous);
		Previous.setVisible(false);
		
		PlayStop.addActionListener(this);
		PlayStop.setBounds(470, 400, 60, 30);
		add(PlayStop);
		PlayStop.setVisible(false);
		
		Next.addActionListener(this);
		Next.setBounds(530, 400, 40, 30);
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
	

	public void actionPerformed(ActionEvent e) {/** action listener function that execute commands based on the button being pressed of option being selected **/
		Input(e.getActionCommand()); 
	} 
	

	@SuppressWarnings({ "static-access", "deprecation" })
	public void Input(String input) {
						command = "";
		switch (input){ /** check which button has been pressed **/
		
			case "SET-CRUISE-CONTROL":
						command = "c";
					break;
			case "SEAT-BELT-ENGAGED":
						command = "b";
					break;
			case "DRIVE":
						command = "d";
					break;
			case "PARK":
						command = "n";
					break;
			case "REVERSE":
						command = "r";
					break;
			case "BRAKE":
					if(JOptionPane.showConfirmDialog(null, "HOLD-BRAKE?", "TOC", 0) == 0) {
						command = "h";
						getSpeedometer().setBounds(329, 200, 300, 100);
					}else{
						command = "p";  
					}
					break;
			case "ACCELERATE":
						command = "a";  
					break;
			case "<":
						if(State.selectedSong > 1)
							State.selectedSong --;
						else
							State.selectedSong = 2;
						State.songCover(String.valueOf(State.selectedSong)); 
						if(State.Play) {
							getPlayStop().setText("stop");
							State.playSong();
						}
					break;
			case "play":
						getRadio().setVisible(true);
						State.playSong();
						getPlayStop().setText("stop");
						State.Play = true;
					break;
			case "stop":
						getPlayStop().setText("play");
						State.getMp3Player().getSong().stop();
						State.Play = false;
					break;
			case">":
						if(State.selectedSong < 2)
							State.selectedSong ++;
						else
							State.selectedSong = 1;
						State.songCover(String.valueOf(State.selectedSong));
						if(State.Play) {
							getPlayStop().setText("stop");
							State.playSong();
						}
					break;
			default: 
						State.getLogger().error(input + " Action not Recognized");
		}
		if(!command.equals(""))
			process(command);
	}

	public void process(String input) {/** this function checks if the input is a part of the accepted alphabet **/
		setAccepted(false);// set accept to false by default 
		if (isApartOfTheLanguage(input))
			vehicleControl(input);
		else 
			State.getLogger().error( "\""+ input + "\" is not a recognized/acceptable Input");
		
	}
	
	public static void vehicleControl(String command) { // this function controls the states transitions
		
		try {
			State.Control(command);/// pass the command through the state, this function also change the states
			StateLabel.setText(State.getName()); // set the displayed label to the name of the current state
			
			if(!(State.getName().equals("OFF") || State.getName().equals("HOLD")))
				getSpeedometer().setText("" + getSpeed());
			
			input.setText(command);// display the current input signal, if input is accepted the color of the input is green and if the input is not accepted then the color turns red  
			if(isAccepted)
				input.setForeground(Color.GREEN);
			else 
				input.setForeground(Color.RED);
			
			ProcessString.setText(getInputArray().toString().replaceAll(", ", ""));
			shift();
		}catch (InterruptedException e) {
			State.getLogger().error("InterruptedException Caught"); 
		}
	}
	
	public boolean isApartOfTheLanguage(String letter){
	    for(int i = 0; i < Alphabet.length(); i++) { // iterate to the length of the alphabet
	        if( Alphabet.charAt(i) == letter.charAt(0))// check the character at the index of the alphabet with the given letter
	            return true;// return true if the input is a part of the language
	    }
		return false;// return false if the input is not a part of the language
	}
	
	
	public static void shift() { // controls the speed-text in the right clock on the dash and the gear-text in the left clock  
		if(State.getName().equals("IN-FORWARD-MOTION") || State.getName().equals("CRUISE-CONTROL-ENGAGED")) {
			if(getSpeed() > 0 && getSpeed() <= 27)
				getGear().setText("1");
			else if(getSpeed() > 27 && getSpeed() <= 54)
				getGear().setText("2");
			else if(getSpeed() > 54 && getSpeed() <= 81)
				getGear().setText("3");
			else if(getSpeed() > 81 && getSpeed() <= 108)
				getGear().setText("4");
			else if(getSpeed() > 108 && getSpeed() <= 135)
				getGear().setText("5");
			else if(getSpeed() > 135 && getSpeed() <= 162)
				getGear().setText("6");
			else if(getSpeed() > 162 && getSpeed() <= 189)
				getGear().setText("7");
			else if(getSpeed() > 189 && getSpeed() <= 220)
				getGear().setText("8");
			else if(getSpeed() > 220)// allows the car speed to max at 220 in forward motion 
				setSpeed(220);
		}else if(State.getName().equals("IN-REVERSE-MOTION")) {
			getGear().setText("R");
			if(getSpeed() >= 30)// allows the car speed to max at 30 in reverse motion
				setSpeed(30);
		}else {
			getGear().setText("");
		}
	}
	
	
	public boolean getAcceptStatus() {
		return isAccepted;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		 process(String.valueOf(e.getKeyChar()));// get input from the keyboard
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}


	public static ArrayList<String> getInputArray() {
		return InputArray;
	}


	public static JLabel getProcessString() {
		return ProcessString;
	}


	public static void setAccepted(boolean is) {
		isAccepted = is;
	}


	public static void setState(State state) {
		State = state;
	}

	public static JLabel getStateLabel() {
		return StateLabel;
	}


	public static JComboBox<String> getStickShift() {
		return stickShift;
	}


	public static JLabel getSpeedometer() {
		return Speedometer;
	}


	public static JLabel getSeatbelt() {
		return seatbelt;
	}


	public static int getSpeed() {
		return Speed;
	}


	public static void setSpeed(int speed) {
		Speed = speed;
	}


	public static JLabel getGear() {
		return Gear;
	}


	public static JLabel getGearIndicatior() {
		return GearIndicatior;
	}


	public static JLabel getReverseCam() {
		return reverseCam;
	}


	public static JLabel getRadio() {
		return radio;
	}


	public static JButton getPrevious() {
		return Previous;
	}


	public static JButton getPlayStop() {
		return PlayStop;
	}


	public static JButton getNext() {
		return Next;
	}
}
