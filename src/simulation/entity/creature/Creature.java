package simulation.entity.creature;

import simulation.TargetSearcher;
import simulation.entity.Entity;
import simulation.map.Coordinate;

public abstract class Creature extends Entity {

    private int speed;
    private int health;

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove(Coordinate coordinate, TargetSearcher searcher);

    public abstract <T extends Entity> Class<T> getTargetType();
}
