package simulation.action.turn;

import simulation.TargetSearcher;
import simulation.action.Action;
import simulation.map.GameMap;

public abstract class TurnAction extends Action {

    protected final TargetSearcher searcher;

    public TurnAction(GameMap map, TargetSearcher searcher) {
        super(map);
        this.searcher = searcher;
    }
}