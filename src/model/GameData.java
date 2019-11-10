package model;

import java.util.ArrayList;

public class GameData {

    public ArrayList<GameFigure> fixedObject = new ArrayList<>();
    public ArrayList<GameFigure> friendObject = new ArrayList<>();
    public ArrayList<GameFigure> enemyObject = new ArrayList<>();

    public void update() {

        for (var fig: fixedObject) {
            fig.update();
        }

        for (var fig: friendObject) {
            fig.update();
        }

        for (var fig: enemyObject) {
            fig.update();
        }

    }

    public void clear() {
        fixedObject.clear();
        friendObject.clear();
        enemyObject.clear();
    }
}
