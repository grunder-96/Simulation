package simulation.entity.creature;

import simulation.TargetSearcher;
import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.map.Coordinate;
import simulation.map.GameMap;

import java.lang.annotation.Target;
import java.util.List;

public class Herbivore extends Creature {

    private TargetSearcher searcher;

    public Herbivore() {
        super(2, 5);
    }

    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    @Override
    public void makeMove(Coordinate coordinate, GameMap map, TargetSearcher searcher) {
        List<Coordinate> shortestWay = searcher.findShortestWay(coordinate, this);
        if (shortestWay.isEmpty()) {
            return;
        }
        map.removeEntity(coordinate);
        map.putEntity(shortestWay.getFirst(), this);
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
