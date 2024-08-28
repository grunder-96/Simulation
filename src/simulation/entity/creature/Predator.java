package simulation.entity.creature;

public class Predator extends Creature {

    private int attack;

    public Predator(int speed, int health, int attack) {
        super(speed, health);
        this.attack = attack;
    }

    @Override
    public void makeMove() {

    }
}
