package view;

import controller.MouseEventListener;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    public MyCanvas canvas;

    public void init() {
        setSize(1000,600);
        setLocation(300, 200);
        setTitle("Game Engine");

        canvas = new MyCanvas();

        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);
    }
}
