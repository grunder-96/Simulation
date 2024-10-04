package simulation.entity.creature;

import simulation.TargetSearcher;
import simulation.entity.Grass;
import simulation.map.Coordinate;
import simulation.map.GameMap;

public class Herbivore extends Creature {

    private TargetSearcher searcher;

    public Herbivore() {
        super(6);
    }

    public Herbivore(int health) {
        super(health);
    }

    @Override
    public void makeMove(Coordinate coordinate, GameMap map, TargetSearcher searcher) {
        moveToEntity(coordinate, map, searcher);
    }

    @Override
    public void eat(GameMap map, Coordinate sourceCoordinate, Coordinate targetCoordinate) {
        map.removeEntity(targetCoordinate);
        Creature creature  = (Creature) map.getEntity(sourceCoordinate).get();
        creature.increaseHealth(1);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC10";
    }

    @Override
    public Class<Grass> getTargetType() {
        return Grass.class;
    }
}
