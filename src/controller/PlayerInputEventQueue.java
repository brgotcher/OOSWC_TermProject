package controller;

import model.Bullet;
import model.MousePointer;

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
                    Bullet b = new Bullet(e.getX(),e.getY());
                    Main.gameData.friendObject.add(b);
                    break;
                case InputEvent.MOUSE_MOVED:
                    MousePointer mp = (MousePointer) Main.gameData.fixedObject.get(0);
                    MouseEvent me = (MouseEvent) inputEvent.event;
                    mp.location.x = me.getX();
                    mp.location.y = me.getY();
                    break;
            }
        }
    }
}
