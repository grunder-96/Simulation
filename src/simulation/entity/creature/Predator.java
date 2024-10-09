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
        moveToEntity(coordinate, map, searcher);
    }

    @Override
    public void eat(GameMap map, Coordinate sourceCoordinate, Coordinate targetCoordinate) {
        Creature creature = (Creature) map.getEntity(targetCoordinate).get();
        creature.decreaseHealth(attack);
        if (creature.isDead) {
            map.removeEntity(targetCoordinate);
        }
    }

    @Override
    public Class<Herbivore> getTargetType() {
        return Herbivore.class;
    }
}
