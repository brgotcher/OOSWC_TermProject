package model;

import java.awt.*;

public class Bullet extends GameFigure {

    public Bullet(int x, int y) {
        super(x, y);
    }


    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fillOval((int)super.location.x,(int)super.location.y,100,30);
    }

    @Override
    public void update() {
        ++super.location.x;
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
