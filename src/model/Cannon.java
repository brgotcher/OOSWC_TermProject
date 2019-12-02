package model;

import controller.Main;
import model.cannonball.CannonBall;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Cannon extends GameFigure {

    public final int BODY = 30;
    public final int CANNON = 35;
    public Rectangle2D.Float body;
    public Line2D.Float cannon;
    public int hp;
    public Color color = Color.YELLOW;

    public Cannon(int x, int y) {
        super(x,y);
        body = new Rectangle2D.Float((float) x-BODY/2, (float) y-BODY/2, (float) BODY, (float) BODY);
        cannon = new Line2D.Float(x, y, x - CANNON, y);
        hp = 2;
    }

    public void fire() {
        System.out.println("firing");
        CannonBall cannonball = new CannonBall((int) super.location.x-CANNON, (int) this.location.y);
        Main.gameData.enemyObject.add(cannonball);
        System.out.println("fired");
    }

    @Override
    public void render(Graphics2D g2) {
        if (hp ==1) color = Color.MAGENTA;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(4));
        g2.draw(body);
        g2.draw(cannon);

    }

    @Override
    public void update() {

    }

    @Override
    public int getCollisionRadius() {
        return 15;
    }
}
