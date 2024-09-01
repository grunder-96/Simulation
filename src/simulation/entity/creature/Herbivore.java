package simulation.entity.creature;

import simulation.entity.Entity;
import simulation.entity.Grass;

public class Herbivore extends Creature {

    public Herbivore() {
        super(2, 5);
    }

    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    @Override
    public void makeMove() {

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
