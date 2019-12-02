package model.cannonball;

import model.Base;
import model.GameFigure;

import java.awt.*;

import static controller.Main.wave;

public class CannonBall extends GameFigure {

    int size = 8;
    final int MAX_CANNONBALL_SIZE = 25;
    Color color = Color.WHITE;
    public static final int STATE_SHOOTING = 0;
    public static final int STATE_EXPLODING = 1;
    public static final int STATE_DONE = 2;
    public int state;
    public CannonBallAnimStrategy animStrategy;




    public CannonBall(int x, int y) {
        super(x,y);
        state = STATE_SHOOTING;
        animStrategy = new CannonBallAnimShooting(this);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.fillOval((int) super.location.x - size/2, (int) super.location.y - size/2, size, size);
    }

    @Override
    public void update() {
        updateState();
        animStrategy.animate();

//        if (state == STATE_SHOOTING) {
//            super.location.x -= (2.2 * wave + 3);
//        } else if (state == STATE_EXPLODING) {
//            color = Color.RED;
//            size++;
//        } else {
//            Base.hp -= 10;
//
//        }

//        if (super.location.x >= 110) {
//            super.location.x -= (2.2 * wave + 3);
//        } else {
//            color = Color.RED;
//            if (size < 20) {
//                size++;
//            } else {
//                Base.hp -= 10;
//                super.done = true;
//            }
//        }
    }

    private void updateState() {
        if (state == STATE_SHOOTING) {
            if (super.location.x < 110) {
                Base.hp -= 10;
                state = STATE_EXPLODING;
                animStrategy = new CannonBallAnimExploding(this);
            }
        } else if (state == STATE_EXPLODING) {
            if (size >= MAX_CANNONBALL_SIZE) {
                super.done = true;
            }
        }
    }

    @Override
    public int getCollisionRadius() {
        return 3;
    }
}
