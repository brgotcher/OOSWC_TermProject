package controller;

import model.Bullet;
import model.GameData;
import view.MyWindow;

import javax.swing.*;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static boolean running;

    public static int FPS = 20; //frames per second

    public static void main(String[] args) {

        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();

        initGame();
        gameLoop();

    }

    static void initGame() {
        gameData.friendObject.add(new Bullet(50, 50));
    }

    static void gameLoop() {

        running = true;

        //game loop
        while (running) {
            long startTime = System.currentTimeMillis();
            gameData.update();
            win.canvas.render();
            long endTime = System.currentTimeMillis();

            long timeSpent = endTime - startTime;
            long sleepTime = (long) (1000 / FPS - timeSpent);

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
