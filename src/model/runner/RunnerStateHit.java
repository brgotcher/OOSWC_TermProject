package model.runner;

import java.awt.*;

public class RunnerStateHit implements RunnerState {

    Runner context;

    public RunnerStateHit(Runner context) {
        this.context = context;
    }

    @Override
    public void goNext(Runner context) {
        //context.done = true;
        context.setState(new RunnerStateDone(context));
    }

}
