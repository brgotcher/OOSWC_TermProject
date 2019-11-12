package model;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class GameFigure {

    public Point2D.Float location;
    public boolean done = false;

    public GameFigure(float x, float y) {
        location = new Point2D.Float(x,y);
    }

    public GameFigure() {
        this(0,0);
    }

    public void setLocation(float x, float y) {
        location.x = x;
        location.y = y;
    }

    public boolean collideWith(GameFigure f) {
        double dist = this.location.distance(f.location);
        if (dist <= this.getCollisionRadius() + f.getCollisionRadius()) {
            return true;
        } else {
            return false;
        }
    }

    public abstract void render(Graphics2D g2);
    public abstract void update();
    public abstract int getCollisionRadius();
}
