package com.image;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class DrawPole extends JComponent {

    public int x = 10;
    public int y = 100;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(x, y, 10, 100);
        g.fillRect(x, y, 10, 100);
        g.setColor(Color.BLACK);
    }

    public void moveRight() {
        x = x + 5;
        repaint();
    }

    public void moveLeft() {
        x = x - 5;
        repaint();
    }

    public void moveDown() {
        y = y + 5;
        repaint();
    }

    public void moveUp() {
        y = y - 5;
        repaint();
    }
}