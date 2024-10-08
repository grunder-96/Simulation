package simulation.action;

import simulation.map.GameMap;

public abstract class Action {

    protected GameMap map;

    public Action(GameMap map) {
        this.map = map;
    }

    public abstract void doAction();
}
