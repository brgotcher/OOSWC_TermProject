package model;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HighScores extends GameFigure {

    public static int[] scores = new int[10];
    Color color;
    Font font;
    int temp;

    public HighScores(int[] scores, int x, int y, Color color, Font font) {
        super(x,y);
        this.scores = scores;
        this.color = color;
        this.font = font;
    }

    public static void getScores() {
        try {
            File scorelist = new File("scorelist.txt");
            Scanner data = new Scanner(scorelist);
            int indx = 0;
            while (data.hasNextLine()) {
                scores[indx] = Integer.parseInt(data.nextLine());
                indx++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void addScore(int newScore) {
        int oldScore = scores[0];
        int i = 0;
        while (newScore < oldScore) {
            if (i == 9) {
                newScore = scores[i];
                break;
            }
            oldScore = scores[++i];
        }
        for (int j = i; j < 10; j++) {
            temp = scores[j];
            scores[j] = newScore;
            newScore = temp;
        }
        
        
    }


    @Override
    public void render(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(color.RED);
        for (int i = 0; i < 10; i++) {
            String thisScore = Integer.toString(i+1) + ":  " + Integer.toString(scores[i]);
            g2.drawString(thisScore, (int) (location.x), (int) (location.y + 50*i));

        }

    }

    @Override
    public void update() {

    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
