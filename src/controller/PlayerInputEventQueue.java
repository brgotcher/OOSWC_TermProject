package controller;

import model.Bullet;
import model.Guard;
import model.MousePointer;
import view.MyWindow;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class PlayerInputEventQueue {

    public LinkedList<InputEvent> queue = new LinkedList<>();

    public void processInputEvents() {

        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();

            switch (inputEvent.type) {
                case InputEvent.MOUSE_PRESSED:
                    MouseEvent e = (MouseEvent) inputEvent.event;
                    Bullet b = new Bullet(e.getX(), e.getY());
                    Main.gameData.friendObject.add(b);
                    break;
                case InputEvent.MOUSE_MOVED:
                    MousePointer mp = (MousePointer) Main.gameData.fixedObject.get(0);
                    MouseEvent me = (MouseEvent) inputEvent.event;
                    mp.location.x = me.getX();
                    mp.location.y = me.getY();
                    break;
                case InputEvent.KEY_PRESSED:
                    var guard = Main.gameData.fixedObject.get(Main.INDEX_GUARD);
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_W:
                            if (guard.location.y > Guard.UNIT_MOVE + Guard.BODY_HEIGHT/2) guard.location.y -= Guard.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_A:
                            if (guard.location.x > Guard.UNIT_MOVE) guard.location.x -= Guard.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_S:
                            if (guard.location.y < MyWindow.HEIGHT - (Guard.UNIT_MOVE + Guard.BODY_HEIGHT * 2)) guard.location.y += Guard.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_D:
                            if (guard.location.x < MyWindow.WIDTH - (Guard.UNIT_MOVE + Guard.BODY_WIDTH)) guard.location.x += Guard.UNIT_MOVE;
                            break;
                    }
                    break;
            }
        }
    }
}
