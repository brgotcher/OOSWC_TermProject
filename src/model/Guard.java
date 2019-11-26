package model;

import controller.Main;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;

public class Guard extends GameFigure {

    public static final int BODY_HEIGHT = 30;
    public static final int BODY_WIDTH = 15;
    public final int BARREL_LEN = 15;
    public static final int UNIT_MOVE = 10;
    public Ellipse2D.Float body;
    public Ellipse2D.Float head;
    public Line2D.Float barrel;
    public Line2D.Float leftleg;
    public Line2D.Float rightleg;
    public static int hp;

    public Guard(int x, int y) {
        super(x, y);
        body = new Ellipse2D.Float(x - BODY_WIDTH/2, y - BODY_HEIGHT/2, BODY_WIDTH, BODY_HEIGHT);
        head = new Ellipse2D.Float(x - BODY_WIDTH/2, y - BODY_HEIGHT, BODY_WIDTH, BODY_HEIGHT/2 );
        barrel = new Line2D.Float(x, y, x, y - BARREL_LEN);
        leftleg = new Line2D.Float(x-BODY_WIDTH/4, y+BODY_HEIGHT/2, x-BODY_WIDTH/2, y + BODY_HEIGHT);
        rightleg = new Line2D.Float(x+BODY_WIDTH/4, y+BODY_HEIGHT/2, x+BODY_WIDTH/2, y + BODY_HEIGHT);
        hp = 20;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(3));
        g2.draw(barrel);
        g2.draw(body);
        g2.draw(head);
        g2.draw(leftleg);
        g2.draw(rightleg);
    }

    @Override
    public void update() {
        MousePointer mousePointer = (MousePointer) Main.gameData.fixedObject.get(Main.INDEX_MOUSE_POINTER);
        float tx = mousePointer.location.x;
        float ty = mousePointer.location.y;
        double rad = Math.atan2(ty - super.location.y, tx - super.location.x);
        float barrel_y = (float) (BARREL_LEN * Math.sin(rad));
        float barrel_x = (float) (BARREL_LEN * Math.cos(rad));

        barrel.x1 = super.location.x;
        barrel.y1 = super.location.y;
        barrel.x2 = super.location.x + barrel_x;
        barrel.y2 = super.location.y + barrel_y;

        body.x = location.x - BODY_WIDTH / 2;
        body.y = location.y - BODY_HEIGHT / 2;

        head.x = location.x - BODY_WIDTH / 2;
        head.y = location.y - BODY_HEIGHT;

        leftleg.x1 = location.x - BODY_WIDTH/4;
        leftleg.x2 = location.x - BODY_WIDTH/2;
        leftleg.y1 = location.y + BODY_HEIGHT/2;
        leftleg.y2 = location.y + BODY_HEIGHT;

        rightleg.x1 = location.x + BODY_WIDTH/4;
        rightleg.x2 = location.x + BODY_WIDTH/2;
        rightleg.y1 = location.y + BODY_HEIGHT/2;
        rightleg.y2 = location.y + BODY_HEIGHT;


    }

    @Override
    public int getCollisionRadius() {
        return 22;
    }
}
