package simulation.entity.creature;

import simulation.entity.Entity;

public class Predator extends Creature {

    private int attack;

    public Predator() {
        super(3,1);
        attack = 2;
    }

    public Predator(int speed, int health, int attack) {
        super(speed, health);
        this.attack = attack;
    }

    @Override
    public void makeMove() {

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
