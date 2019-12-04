package model.runner;

import model.Base;

import static controller.Main.wave;

public class RunnerStateRunning implements RunnerState {

    Runner context;

    public RunnerStateRunning(Runner context) {
        this.context = context;

    }

    @Override
    public void goNext(Runner context) {
        context.setState(new RunnerStateHit(context));
        context.animStrategy = new RunnerAnimHit(context);
    }


}
