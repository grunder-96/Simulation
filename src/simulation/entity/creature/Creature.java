package simulation.entity.creature;

import simulation.TargetSearcher;
import simulation.entity.Entity;
import simulation.map.Coordinate;
import simulation.map.GameMap;

public abstract class Creature extends Entity {

    private int speed;
    private int health;

    public Creature(int health) {
        this.speed = 1;
        this.health = health;
    }

    public abstract void makeMove(Coordinate coordinate, GameMap map, TargetSearcher searcher);

    public abstract <T extends Entity> Class<T> getTargetType();
}
