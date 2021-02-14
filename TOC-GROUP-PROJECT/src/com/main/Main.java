package com.main;

import java.awt.EventQueue;
import com.domain.UTuvcs;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UTuvcs("UTuvcs");
			}
		});
	}
}
