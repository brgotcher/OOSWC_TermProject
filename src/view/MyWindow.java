package view;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    public MyCanvas canvas;

    public void init() {
        setSize(1400,1000);
        setLocation(300, 200);
        setTitle("Game Engine");

        canvas = new MyCanvas();

        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);
    }
}
