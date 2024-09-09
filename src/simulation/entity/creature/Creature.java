package simulation.entity.creature;

import simulation.TargetSearcher;
import simulation.entity.Entity;
import simulation.map.Coordinate;
import simulation.map.GameMap;

import java.util.List;

public abstract class Creature extends Entity {

    private int speed;
    private int health;
    protected boolean isDead;

    public Creature(int health) {
        this.speed = 1;
        this.health = health;
    }

    protected void decreaseHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            isDead = true;
        }
    }

    protected void increaseHealth(int health) {
        health += health;
    }

    public abstract void makeMove(Coordinate coordinate, GameMap map, TargetSearcher searcher);

    protected void moveToEntity(Coordinate coordinate, GameMap map, TargetSearcher searcher) {
        List<Coordinate> shortestWay = searcher.findShortestWay(coordinate, this);
        if (shortestWay.isEmpty()) {
            return;
        }
        if (shortestWay.size() == 1) {
            eat(map, coordinate, shortestWay.getFirst());
            return;
        }
        map.removeEntity(coordinate);
        map.putEntity(shortestWay.getFirst(), this);
    }

    public abstract void eat(GameMap map, Coordinate sourceCoordinate, Coordinate targetCoordinate);

    public abstract <T extends Entity> Class<T> getTargetType();
}
