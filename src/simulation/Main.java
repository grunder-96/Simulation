package simulation;

import simulation.map.GameMap;

public class Main {

    public static void main(String[] args) {
        GameMap map = new GameMap(20, 15);
        new Simulation(map).startSimulation();
    }
}