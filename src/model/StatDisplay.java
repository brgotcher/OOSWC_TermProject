package model;

import controller.Main;

import java.awt.*;

import static controller.Main.*;

public class StatDisplay extends GameFigure {

    public String score;
    public String baseHP;
    public String guardHP;
    public String wave;
    Font font = new Font("Monospace", Font.ITALIC, 20);

    public StatDisplay(int x, int y) {
        super(x,y);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + Integer.toString(counter), 350, 40);
        g2.drawString("guard HP: " + Integer.toString(Guard.hp), 350, 70);
        g2.drawString("base hp: " + Integer.toString(Base.hp), 350, 100);
        g2.drawString("Wave: " + Integer.toString(Main.wave),350, 130);
    }

    @Override
    public void update() {

    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
