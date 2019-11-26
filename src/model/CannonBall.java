package model;

import java.awt.*;

import static controller.Main.wave;

public class CannonBall extends GameFigure{

    int size = 8;
    Color color = Color.WHITE;


    CannonBall(int x, int y) {
        super(x,y);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.fillOval((int) super.location.x - size/2, (int) super.location.y - size/2, size, size);
    }

    @Override
    public void update() {
        if (super.location.x >= 110) {
            super.location.x -= (2.2 * wave + 3);
        } else {
            color = Color.RED;
            if (size < 20) {
                size++;
            } else {
                Base.hp -= 10;
                super.done = true;
            }
        }
    }

    @Override
    public int getCollisionRadius() {
        return 5;
    }
}
