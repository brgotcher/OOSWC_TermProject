package model.runner;

public class RunnerStateDone implements RunnerState {

    Runner context;

    public RunnerStateDone(Runner context) {
        this.context = context;
        context.done = true;
    }

    @Override
    public void goNext(Runner context) {
        context.setState(null);
    }

    @Override
    public void animate() {

    }
}
