package model;

import java.awt.*;
import java.awt.geom.Line2D;

public class Base extends GameFigure {

    int t = 20;
    int b = 520;
    int l = 25;
    int r = 100;
    public static Line2D.Float top, bottom, left, right;
    public static int hp;

    public Base(int x, int y) {
        super(x,y);
        top = new Line2D.Float(l, t, r, t);
        bottom = new Line2D.Float(l, b, r, b);
        left = new Line2D.Float(l, t, l, b);
        right = new Line2D.Float(r, t, r, b);
        hp = 20;
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(3));
        g2.draw(top);
        g2.draw(bottom);
        g2.draw(left);
        g2.draw(right);
        Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 50);
        g2.setFont(font);
        g2.drawString("B", l + 20, t + 100);
        g2.drawString("A", l + 20, t + 200);
        g2.drawString("S", l + 20, t + 300);
        g2.drawString("E", l + 20, t + 400);
    }

    @Override
    public void update() {

    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
