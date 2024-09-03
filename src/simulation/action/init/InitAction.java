package simulation.action.init;

import simulation.action.Action;
import simulation.map.GameMap;

public abstract class InitAction extends Action {

    public InitAction(GameMap map) {
        super(map);
    }
}