package model.runner;

import java.awt.*;

public class RunnerAnimHit implements RunnerAnimStrategy {

    Runner context;

    public RunnerAnimHit(Runner context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.RED;
        if (context.location.x >= 115) context.size--;
        else context.size++;
    }

}
