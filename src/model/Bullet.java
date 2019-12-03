package model;

import controller.Main;
import view.MyWindow;

import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet extends GameFigure {

    public static final int UNIT_MOVE = 8;
    public static final int BULLET_SIZE = 5;


    int size = BULLET_SIZE;

    Point2D.Float target;
    Color color;
    double xmove;
    double ymove;


    public Bullet(int tx, int ty) {
        Guard guard = (Guard) Main.gameData.fixedObject.get(Main.INDEX_GUARD);
        super.location.x = guard.barrel.x2;
        super.location.y = guard.barrel.y2;
        target = new Point2D.Float(tx, ty);
        color = Color.GREEN;
        double rad = Math.atan2(target.y - location.y, target.x - location.x);
        xmove = UNIT_MOVE * Math.cos(rad);
        ymove = UNIT_MOVE * Math.sin(rad);
    }


    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval((int)super.location.x - size/2,(int)super.location.y - size/2,size,size);
    }


    @Override
    public void update() {

        if (location.x >= MyWindow.WIDTH || location.x <= 0 || location.y <= 0 || location.y >= MyWindow.HEIGHT) {
            super.done = true;
        } else {
            location.x += xmove;
            location.y += ymove;
        }
    }

    @Override
    public int getCollisionRadius() {
        return 5;
    }
}
