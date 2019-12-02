package model.runner;

import model.Base;
import model.GameFigure;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import static controller.Main.wave;

public class Runner extends GameFigure {

    public int size = 20;
    public final int MAX_SIZE = 40;
    public final int MIN_SIZE = 8;
    public int height;
    public int width;
    public Ellipse2D.Float body;
    public Ellipse2D.Float head;
    public Line2D.Float leftleg;
    public Line2D.Float rightleg;
    Color color = Color.BLUE;

    public RunnerState state;


    public Runner(int x, int y) {
        super(x,y);
        body = new Ellipse2D.Float(x - size/4, y - size/2, size/2, size);
        head = new Ellipse2D.Float(x - size/4, y - size, size/2, size/2 );
        leftleg = new Line2D.Float(x-size/8, y+size/2, x-size/4, y + size);
        rightleg = new Line2D.Float(x+size/8, y+size/2, x+size/4, y + size);
        state = new RunnerStateRunning(this);
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
        state.animate();

//        body.x = location.x - size / 4;
//        body.y = location.y - size / 2;
//
//        head.x = location.x - size / 4;
//        head.y = location.y - size;
//
//        leftleg.x1 = location.x - size/8;
//        leftleg.x2 = location.x - size/4;
//        leftleg.y1 = location.y + size/2;
//        leftleg.y2 = location.y + size;
//
//        rightleg.x1 = location.x + size/8;
//        rightleg.x2 = location.x + size/4;
//        rightleg.y1 = location.y + size/2;
//        rightleg.y2 = location.y + size;

//        if (location.x >= 110) {
//            location.x -= (2 * wave + 3);
//        } else {
//            Base.hp -= 10;
//            super.done = true;
//        }
    }

    public void updateState() {
        if (state.getClass() == RunnerStateRunning.class && super.location.x <= 110) {
            goNextState();
        }
        if (size > MAX_SIZE || size < MIN_SIZE) {
            goNextState();
        }

    }

    @Override
    public int getCollisionRadius() {
        return 15;
    }
}
