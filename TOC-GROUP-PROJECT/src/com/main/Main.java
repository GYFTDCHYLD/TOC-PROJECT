package com.main;

import java.awt.EventQueue;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.domain.UTuvcs;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Scanner in = new Scanner(System.in);
				UTuvcs UTuvcs = new UTuvcs("UTuvcs");
				//System.out.println("Enter Input String: ");
				//autoRun(UTuvcs,in.next());
				//autoRun(UTuvcs,"shsbrhdcppns");
			}
		});
	}
	public static void autoRun(UTuvcs UTuvcs, String string){ // loop through an input string and run the commands 
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int count = 0; 
			int i = 0;
			public void run() {
				count ++;
				if(count%5 == 0) { // trigger a command every 5 seconds
					UTuvcs.process(String.valueOf(string.charAt(i)));
					if(UTuvcs.getAcceptStatus() == false) {
						System.err.println("String rejected");
						timer.cancel();// stop and an invalid command and reject the string
					}i++;
				}
				if(i == string.length())
					timer.cancel();// end the counter/loop after executing the last/final command
			}	
		}, 0, 1000);
	}
}


