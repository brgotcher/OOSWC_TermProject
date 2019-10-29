package view;

import controller.KeyEventListener;
import controller.MouseEventListener;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    public MyCanvas canvas;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    public void init() {
        setSize(WIDTH,HEIGHT);
        setLocation(300, 200);
        setTitle("Game Engine");

        canvas = new MyCanvas();

        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        KeyEventListener keyEventListener = new KeyEventListener();
        canvas.addKeyListener(keyEventListener);
        canvas.setFocusable(true);

        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);
    }
}
