package model.runner;

import java.awt.*;

public class RunnerStateHit implements RunnerState {

    Runner context;

    public RunnerStateHit(Runner context) {
        this.context = context;
    }

    @Override
    public void goNext(Runner context) {
        context.done = true;
        context.setState(new RunnerStateDone(context));
    }

//    @Override
//    public void animate() {
//        context.color = Color.RED;
//        if (context.location.x >= 115) context.size--;
//        else context.size++;
//    }
}
