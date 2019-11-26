package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import static controller.Main.wave;

public class Runner extends GameFigure {

    public final int BODY_HEIGHT = 20;
    public final int BODY_WIDTH = 10;
    public Ellipse2D.Float body;
    public Ellipse2D.Float head;
    public Line2D.Float leftleg;
    public Line2D.Float rightleg;

    public Runner(int x, int y) {
        super(x,y);
        body = new Ellipse2D.Float(x - BODY_WIDTH/2, y - BODY_HEIGHT/2, BODY_WIDTH, BODY_HEIGHT);
        head = new Ellipse2D.Float(x - BODY_WIDTH/2, y - BODY_HEIGHT, BODY_WIDTH, BODY_HEIGHT/2 );
        leftleg = new Line2D.Float(x-BODY_WIDTH/4, y+BODY_HEIGHT/2, x-BODY_WIDTH/2, y + BODY_HEIGHT);
        rightleg = new Line2D.Float(x+BODY_WIDTH/4, y+BODY_HEIGHT/2, x+BODY_WIDTH/2, y + BODY_HEIGHT);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(3));
        g2.draw(body);
        g2.draw(head);
        g2.draw(leftleg);
        g2.draw(rightleg);
    }

    @Override
    public void update() {

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

        if (location.x >= 110) {
            location.x -= (2 * wave + 3);
        } else {
            Base.hp -= 10;
            super.done = true;
        }
    }

    @Override
    public int getCollisionRadius() {
        return 15;
    }
}
