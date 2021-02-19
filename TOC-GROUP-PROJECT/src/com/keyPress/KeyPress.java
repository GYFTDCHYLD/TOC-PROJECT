package com.keyPress;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyPress implements KeyListener{
	
	private static String KeyPressed = "";
	
	
	public static KeyAdapter keyAdapter = new KeyAdapter(){
		public void keyPressed(KeyEvent event){
			switch(event.getKeyCode()){
				case KeyEvent.VK_COMMA: setKeyPressed(","); break;
				case KeyEvent.VK_CONTROL: setKeyPressed("CONTROL"); break;
				case KeyEvent.VK_ENTER: setKeyPressed("ENTER"); break;
				case KeyEvent.VK_SHIFT: setKeyPressed("SHIFT"); break;
				case KeyEvent.VK_OPEN_BRACKET: setKeyPressed("("); break;
				case KeyEvent.VK_CLOSE_BRACKET: setKeyPressed(")"); break;
				case KeyEvent.VK_PERIOD: setKeyPressed("."); break;
				case KeyEvent.VK_MINUS: setKeyPressed("-"); break;
				case KeyEvent.VK_ESCAPE: setKeyPressed("EXIT"); break;
				case KeyEvent.VK_A: setKeyPressed("A"); break;
				case KeyEvent.VK_B: setKeyPressed("B"); break;
				case KeyEvent.VK_C: setKeyPressed("C"); break;
				case KeyEvent.VK_D: setKeyPressed("D"); break;
				case KeyEvent.VK_E: setKeyPressed("E"); break;
				case KeyEvent.VK_F: setKeyPressed("F"); break;
				case KeyEvent.VK_G: setKeyPressed("G"); break;
				case KeyEvent.VK_H: setKeyPressed("H"); break;
				case KeyEvent.VK_I: setKeyPressed("I"); break;
				case KeyEvent.VK_J: setKeyPressed("J"); break;
				case KeyEvent.VK_K: setKeyPressed("K"); break;
				case KeyEvent.VK_L: setKeyPressed("L"); break;
				case KeyEvent.VK_M: setKeyPressed("M"); break;
				case KeyEvent.VK_N: setKeyPressed("N"); break;
				case KeyEvent.VK_O: setKeyPressed("O"); break;
				case KeyEvent.VK_P: setKeyPressed("P"); break;
				case KeyEvent.VK_Q: setKeyPressed("Q"); break;
				case KeyEvent.VK_R: setKeyPressed("R"); break;
				case KeyEvent.VK_S: setKeyPressed("S"); break;
				case KeyEvent.VK_T: setKeyPressed("T"); break;
				case KeyEvent.VK_U: setKeyPressed("U"); break;
				case KeyEvent.VK_V: setKeyPressed("V"); break;
				case KeyEvent.VK_W: setKeyPressed("W"); break;
				case KeyEvent.VK_X: setKeyPressed("X"); break;
				case KeyEvent.VK_Y: setKeyPressed("Y"); break;
				case KeyEvent.VK_Z: setKeyPressed("Z"); break;
				case KeyEvent.VK_0: setKeyPressed("0"); break;
				case KeyEvent.VK_1: setKeyPressed("1"); break;
				case KeyEvent.VK_2: setKeyPressed("2"); break;
				case KeyEvent.VK_3: setKeyPressed("3"); break;
				case KeyEvent.VK_4: setKeyPressed("4"); break;
				case KeyEvent.VK_5: setKeyPressed("5"); break;
				case KeyEvent.VK_6: setKeyPressed("6"); break;
				case KeyEvent.VK_7: setKeyPressed("7"); break;
				case KeyEvent.VK_8: setKeyPressed("8"); break;
				case KeyEvent.VK_9: setKeyPressed("9"); break;
				case KeyEvent.VK_NUMPAD0: setKeyPressed("0"); break;
				case KeyEvent.VK_NUMPAD1: setKeyPressed("1"); break;
				case KeyEvent.VK_NUMPAD2: setKeyPressed("2"); break;
				case KeyEvent.VK_NUMPAD3: setKeyPressed("3"); break;
				case KeyEvent.VK_NUMPAD4: setKeyPressed("4"); break;
				case KeyEvent.VK_NUMPAD5: setKeyPressed("5"); break;
				case KeyEvent.VK_NUMPAD6: setKeyPressed("6"); break;
				case KeyEvent.VK_NUMPAD7: setKeyPressed("7"); break;
				case KeyEvent.VK_NUMPAD8: setKeyPressed("8"); break;
				case KeyEvent.VK_NUMPAD9: setKeyPressed("9"); break;
			}
		}
	};
	
	
	public void keyPressed(KeyEvent event) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	public static String getKeyPressed() {
		return KeyPressed;
	}

	public static void setKeyPressed(String keyPressed) {
		KeyPressed = keyPressed;
	}		
}
	
	
	
	
