package controller;

import model.Bullet;
import model.GameData;
import model.Guard;
import model.MousePointer;
import view.MyWindow;

import javax.swing.*;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static PlayerInputEventQueue playerInputEventQueue;
    public static boolean running;

    public static int INDEX_MOUSE_POINTER = 0;
    public static int INDEX_GUARD = 1;

    public static int FPS = 20; //frames per second

    public static void main(String[] args) {

        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();

        initGame();
        gameLoop();

    }

    static void initGame() {
        gameData.fixedObject.add(new MousePointer(0,0));
        int x = 200;
        int y = (Main.win.getHeight() / 2);
        gameData.fixedObject.add(new Guard(x, y));
    }

    static void gameLoop() {

        running = true;

        //game loop
        while (running) {
            long startTime = System.currentTimeMillis();

            playerInputEventQueue.processInputEvents();
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
