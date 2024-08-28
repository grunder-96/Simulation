package simulation.entity.creature;

import simulation.entity.Entity;

public abstract class Creature extends Entity {

    private int speed;
    private int health;

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove();
}
