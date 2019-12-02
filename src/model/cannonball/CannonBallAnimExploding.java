package model.cannonball;

import java.awt.*;

public class CannonBallAnimExploding implements CannonBallAnimStrategy {

    CannonBall context;

    public CannonBallAnimExploding(CannonBall context) {
        this.context = context;
    }
    @Override
    public void animate() {
        context.color = Color.RED;
        context.size++;
    }
}
