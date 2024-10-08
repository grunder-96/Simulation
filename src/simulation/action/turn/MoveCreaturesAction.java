package simulation.action.turn;

import simulation.TargetSearcher;
import simulation.entity.creature.Creature;
import simulation.map.Coordinate;
import simulation.map.GameMap;

import java.util.Set;

public class MoveCreaturesAction extends TurnAction {

    public MoveCreaturesAction(GameMap map, TargetSearcher searcher) {
        super(map, searcher);
    }

    @Override
    public void doAction() {
        Set<Coordinate> creatureCoordinates = map.getCoordinatesByType(Creature.class);
        for (Coordinate coordinate : creatureCoordinates) {
            if (map.isEntityExists(coordinate)) {
                Creature creature = (Creature) map.getEntity(coordinate).get();
                creature.makeMove(coordinate, map, searcher);
            }
        }
    }
}
