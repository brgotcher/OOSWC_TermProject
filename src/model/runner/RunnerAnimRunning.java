package model.runner;

import model.Base;

import static controller.Main.wave;

public class RunnerAnimRunning implements RunnerAnimStrategy {

    Runner context;

    public RunnerAnimRunning(Runner context) {
        this.context = context;
    }


    @Override
    public void animate() {
        context.body.x = context.location.x - context.size / 4;
        context.body.y = context.location.y - context.size / 2;

        context.head.x = context.location.x - context.size / 4;
        context.head.y = context.location.y - context.size;

        context.leftleg.x1 = context.location.x - context.size/8;
        context.leftleg.x2 = context.location.x - context.size/4;
        context.leftleg.y1 = context.location.y + context.size/2;
        context.leftleg.y2 = context.location.y + context.size;

        context.rightleg.x1 = context.location.x + context.size/8;
        context.rightleg.x2 = context.location.x + context.size/4;
        context.rightleg.y1 = context.location.y + context.size/2;
        context.rightleg.y2 = context.location.y + context.size;

        if (context.location.x >= 110) {
            context.location.x -= (2 * wave + 3);
        }

    }
}
