package controller;

import model.*;
import view.MyWindow;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static PlayerInputEventQueue playerInputEventQueue;
    public static boolean running = false;

    public static int INDEX_MOUSE_POINTER = 0;
    public static int INDEX_GUARD = 1;

    public static int FPS = 20; //frames per second

    static Random rand = new Random();

    public static void main(String[] args) {

        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();

        startScreen();

        initGame();

        gameLoop();

    }

    static void startScreen() {

        Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 50);
        gameData.friendObject.add(new Text("Press Start Button", 100, 200, Color.GREEN, font));
        while (!running) {
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void initGame() {
        gameData.clear();
        gameData.fixedObject.add(new MousePointer(0,0));
        int x = 200;
        int y = (Main.win.getHeight() / 2);
        gameData.fixedObject.add(new Guard(x, y));
        // place runner in random y location on far right
        gameData.enemyObject.add(new Runner(MyWindow.WIDTH-50, rand.nextInt(MyWindow.HEIGHT - 50)));
    }

    static void gameLoop() {

        running = true;
        int counter = 1;

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
            if (counter % 100 == 0) {
                gameData.enemyObject.add(new Runner(MyWindow.WIDTH-50, rand.nextInt(MyWindow.HEIGHT - 50)));
            }
            counter++;
        }

    }
}
