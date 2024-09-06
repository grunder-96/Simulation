package simulation.entity.creature;

import simulation.TargetSearcher;
import simulation.entity.Entity;
import simulation.map.Coordinate;
import simulation.map.GameMap;

import java.util.List;

public class Predator extends Creature {

    private int attack;

    public Predator() {
        super(4);
        attack = 2;
    }

    public Predator(int health, int attack) {
        super(health);
        this.attack = attack;
    }

    @Override
    public void makeMove(Coordinate coordinate, GameMap map, TargetSearcher searcher) {
        List<Coordinate> shortestWay = searcher.findShortestWay(coordinate, this);
        if (shortestWay.isEmpty()) {
            return;
        }
        if (shortestWay.size() == 1) {
            Creature creature = (Creature) map.getEntity(shortestWay.getFirst()).get();
            creature.decreaseHealth(attack);
            if (creature.isDead) {
                map.removeEntity(shortestWay.getFirst());
            }
            return;
        }
        map.removeEntity(coordinate);
        map.putEntity(shortestWay.getFirst(), this);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }

    @Override
    public Class<Herbivore> getTargetType() {
        return Herbivore.class;
    }
}
