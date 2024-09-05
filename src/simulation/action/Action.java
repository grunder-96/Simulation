package simulation.action;

import simulation.TargetSearcher;
import simulation.map.GameMap;

public abstract class Action {

    protected GameMap map;
    protected TargetSearcher searcher;

    public Action(GameMap map, TargetSearcher searcher) {
        this.map = map;
        this.searcher = searcher;
    }

    public abstract void doAction();
}
