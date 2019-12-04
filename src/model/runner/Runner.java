package model.runner;

import controller.Main;
import model.Base;
import model.GameFigure;
import model.Guard;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import static controller.Main.gameData;
import static controller.Main.wave;

public class Runner extends GameFigure {

    public int size = 20;
    public final int MAX_SIZE = 40;
    public final int MIN_SIZE = 8;
    public Ellipse2D.Float body;
    public Ellipse2D.Float head;
    public Line2D.Float leftleg;
    public Line2D.Float rightleg;
    Color color = Color.BLUE;

    public RunnerState state;
    public RunnerAnimStrategy animStrategy;
    Guard guard = (Guard) gameData.fixedObject.get(Main.INDEX_GUARD);


    public Runner(int x, int y) {
        super(x,y);
        body = new Ellipse2D.Float(x - size/4, y - size/2, size/2, size);
        head = new Ellipse2D.Float(x - size/4, y - size, size/2, size/2 );
        leftleg = new Line2D.Float(x-size/8, y+size/2, x-size/4, y + size);
        rightleg = new Line2D.Float(x+size/8, y+size/2, x+size/4, y + size);
        state = new RunnerStateRunning(this);
        animStrategy = new RunnerAnimRunning(this);
    }

    public void goNextState() {
        state.goNext(this);
    }

    public void setState(RunnerState state) {
        this.state = state;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(3));
        body = new Ellipse2D.Float(super.location.x - size/4, super.location.y - size/2, size/2, size);
        head = new Ellipse2D.Float(super.location.x - size/4, super.location.y - size, size/2, size/2 );
        leftleg = new Line2D.Float(super.location.x-size/8, super.location.y+size/2, super.location.x-size/4, super.location.y + size);
        rightleg = new Line2D.Float(super.location.x+size/8, super.location.y+size/2, super.location.x+size/4, super.location.y + size);
        g2.draw(body);
        g2.draw(head);
        g2.draw(leftleg);
        g2.draw(rightleg);
    }

    @Override
    public void update() {

        updateState();
        animStrategy.animate();
    }

    public void updateState() {
        if (state.getClass() == RunnerStateRunning.class) {
            if (super.location.x <= 110) {
                Base.hp -= ((wave*2) + 10);
                goNextState();
            } else if (this.collideWith(guard)) {
                guard.hp -= ((wave*2) + 10);
                goNextState();
            } else {
                for (int n = 0; n < gameData.friendObject.size(); n++) {
                    GameFigure blt = gameData.friendObject.get(n);
                    if (this.collideWith(blt)) {
                        blt.done = true;
                        goNextState();
                    }
                }
            }
        } else {
            if (size > MAX_SIZE || size < MIN_SIZE) {
                goNextState();
            }
        }


    }

    @Override
    public int getCollisionRadius() {
        return 13;
    }
}
