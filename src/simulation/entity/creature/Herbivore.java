package simulation.entity.creature;

public class Herbivore extends Creature {

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
}
