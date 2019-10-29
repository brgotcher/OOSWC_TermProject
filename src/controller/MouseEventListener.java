package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed at " + e.getX() + " " + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouse moved at " + e.getX() + " " + e.getY());
    }
}
