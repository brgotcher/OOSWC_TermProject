package model.cannonball;

import static controller.Main.wave;

public class CannonBallAnimShooting implements CannonBallAnimStrategy {

    CannonBall context;

    public CannonBallAnimShooting(CannonBall context) {
        this.context = context;
    }
    @Override
    public void animate() {
        context.location.x -= (2.2 * wave + 3);
    }

}
