package com.domain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UTuvcs extends JFrame{
	private static JFrame frame = new JFrame();
	private static JLabel ProcessString = new JLabel("STRING: ");
	private static JLabel State = new JLabel("OFF");
	private static JLabel Speedometer = new JLabel("");  
	private static JButton START = new JButton("START");
	private static JButton CruiseControl = new JButton("SET-CRUISE-CONTROL");
	private static JButton SeatBelt = new JButton("SEAT-BELT-ENGAGED");
	private static JButton BRAKE = new JButton("BRAKE");
	private static JButton Drive = new JButton("Drive");
	private static JButton Park = new JButton("PARK");
	private static JButton Reverse = new JButton("REVERSE");
	private static JButton ACCELERATE = new JButton("ACCELERATE");
	private static Stack<String> InputStack = new Stack<String>();
	private static String Input;
	private static int Speed = 0;
	
	private String Alphabet = "abcdhnprsz";

	
	public UTuvcs(String name){
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1000, 700); // width, height
		frame.setTitle(name.toUpperCase());
		
		
		
		ProcessString.setSize(260, 40); // width, height
		ProcessString.setLocation(2, 470); // x, y
		ProcessString.setHorizontalAlignment(SwingConstants.LEFT);
		ProcessString.setBounds(10, -40, 1000, 100);
		ProcessString.setFont(new Font("arial", Font.ITALIC, 17));
		frame.add(ProcessString);
		
		
		State.setSize(260, 40); // width, height
		State.setLocation(2, 470); // x, y
		State.setHorizontalAlignment(SwingConstants.LEFT);
		State.setBounds(380, 10, 300, 100);
		State.setFont(new Font("arial", Font.ITALIC, 20));
		frame.add(State);
		

		
		
		Speedometer.setSize(260, 40); // width, height
		Speedometer.setLocation(2, 470); // x, y
		Speedometer.setHorizontalAlignment(SwingConstants.LEFT);
		Speedometer.setBounds(10, 10, 300, 100);
		Speedometer.setFont(new Font("arial", Font.BOLD, 50));
		frame.add(Speedometer);
		
	

		
		START.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		START.setBounds(850, 580, 100, 60);
		frame.getContentPane().add(START);
		
		CruiseControl.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		CruiseControl.setBounds(400, 580, 200, 30);
		frame.getContentPane().add(CruiseControl);
		
		SeatBelt.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		SeatBelt.setBounds(400, 610, 200, 30);
		frame.getContentPane().add(SeatBelt);
		
		Drive.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Drive.setBounds(700, 570, 80, 30);
		frame.getContentPane().add(Drive);
		
		Park.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Park.setBounds(700, 600, 80, 30);
		frame.getContentPane().add(Park);
		
		Reverse.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Reverse.setBounds(700, 630, 80, 30);
		frame.getContentPane().add(Reverse);
		
		
		BRAKE.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		BRAKE.setBounds(150, 550, 200, 100);
		frame.getContentPane().add(BRAKE);
		 
		
		ACCELERATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ACCELERATE.setBounds(10, 450, 100, 200);
		frame.getContentPane().add(ACCELERATE);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("image/dfa.png"));
		label.setBounds(1, -60,1000, 700);
		frame.getContentPane().add(label);
			
	}
	
	public boolean isApartOfTheLanguage(char letter){
	    for(int i = 0; i < Alphabet.length(); i++) { // iterate to the length of the alphabet
	        if( Alphabet.charAt(i) == letter)// check the character at the index of the alphabet with the given letter
	            return true;// return true if the input is a part of the language
	    }
		return false;// return false if the input is not a part of the language
	}

}
