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
    public static boolean run = false;
    public static int wave = 1;

    public static int INDEX_MOUSE_POINTER = 0;
    public static int INDEX_BASE = 1;
    public static int INDEX_GUARD = 2;

    public static int FPS = 20; //frames per second
    public static int counter;

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
        gameData.friendObject.add(new Text("Press Start Button to play", 180, 275, Color.GREEN, font));
        while (!running) {
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void highScores() {
//        gameData.clear();
//        Font font = new Font("serif", Font.BOLD | Font.ITALIC, 50);
//        HighScores.getScores();
//        gameData.friendObject.add(new HighScores(HighScores.scores, 300, 50, Color.GREEN, font));
//        win.buttonPanel.revalidate();
//        win.buttonPanel.repaint();
//        while (!run) {
//            Main.win.canvas.render();
//            System.out.println("test");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    static void initGame() {
        gameData.clear();
        gameData.fixedObject.add(new MousePointer(0,0));
        int x = 200;
        int y = (Main.win.getHeight() / 2);
        gameData.fixedObject.add(new Base(0,0));
        gameData.fixedObject.add(new Guard(x, y));
        gameData.fixedObject.add(new StatDisplay(0,0));
        // place runner in random y location on far right
        gameData.enemyObject.add(new Runner(MyWindow.WIDTH-50, rand.nextInt(MyWindow.HEIGHT - 50)));
    }

    static void gameLoop() {

        running = true;
        counter = 1;

        //game loop
        while (running) {
            long startTime = System.currentTimeMillis();

            playerInputEventQueue.processInputEvents();
            processCollisions();
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
            if (counter % (100 - wave*2) == 0 && counter % (5*(100 - wave*2)) != 0) {
                gameData.enemyObject.add(new Runner(MyWindow.WIDTH-50, rand.nextInt(MyWindow.HEIGHT - 50)));
            }
            else if (counter % (5*(100 - wave * 2)) == 0) {
                gameData.enemyObject.add(new Cannon(MyWindow.WIDTH - 50, rand.nextInt(MyWindow.HEIGHT - 50)));
            }
            counter++;

            if (counter % 2000 == 0) wave++;

            if (Base.hp < 1 | Guard.hp < 1) running = false;
        }

    }

    static void processCollisions() {
        var guard = (Guard) Main.gameData.fixedObject.get(Main.INDEX_GUARD);
        for (var enemy: Main.gameData.enemyObject) {
            if (guard.collideWith(enemy)) {
                guard.hp -= 10;
                enemy.done = true;
            }
        }

        for (var friend: Main.gameData.friendObject) {
            for (var enemy: Main.gameData.enemyObject) {
                if (friend.collideWith(enemy)) {
                    friend.done = true;
                    enemy.done = true;
                }
            }
        }
    }
}
