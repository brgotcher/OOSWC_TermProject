package view;

import controller.KeyEventListener;
import controller.Main;
import controller.MouseEventListener;
import model.HighScores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.Main.gameData;
import static controller.Main.win;

public class MyWindow extends JFrame {

    public MyCanvas canvas;
    public JButton startButton;
    public JButton highScoreButton;
    public JButton quitButton;
    public JPanel buttonPanel;
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

        startButton = new JButton("START");
        highScoreButton = new JButton("HIGH SCORES");
        quitButton = new JButton("QUIT");
        buttonPanel = new JPanel();
        buttonPanel.add(highScoreButton);
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        highScoreButton.setFocusable(false);
        startButton.setFocusable(false);
        quitButton.setFocusable(false);
        cp.add(BorderLayout.SOUTH, buttonPanel);

        ButtonListener buttonlistener = new ButtonListener();
        highScoreButton.addActionListener(buttonlistener);
        startButton.addActionListener(buttonlistener);
        quitButton.addActionListener(buttonlistener);

    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                System.out.println("START");
                buttonPanel.remove(startButton);
                buttonPanel.remove(highScoreButton);
                buttonPanel.revalidate();
                buttonPanel.repaint();
                Main.running = true;
                Main.run = true;

            } else if (e.getSource() == highScoreButton) {
                System.out.println("HIGH SCORES");
                buttonPanel.remove(highScoreButton);
                buttonPanel.revalidate();
                buttonPanel.repaint();
                gameData.clear();
                HighScores.getScores();
                Font font = new Font("serif", Font.BOLD | Font.ITALIC, 50);
                gameData.friendObject.add(new HighScores(HighScores.scores, 300, 50, Color.GREEN, font));
                win.buttonPanel.revalidate();
                win.buttonPanel.repaint();

            } else if (e.getSource() == quitButton) {
                System.out.println("QUIT");
                Main.run = true;
                System.exit(0);
            }
        }
    }
}
