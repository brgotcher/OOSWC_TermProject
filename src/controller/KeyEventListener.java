package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyEventListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W: System.out.println("w key"); break;
            case KeyEvent.VK_S: System.out.println("s key"); break;
            case KeyEvent.VK_D: System.out.println("d key"); break;
            case KeyEvent.VK_A: System.out.println("a key"); break;
        }
    }
}
