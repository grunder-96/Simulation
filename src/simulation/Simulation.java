package simulation;

import simulation.action.Action;
import simulation.action.init.PopulateMapAction;
import simulation.action.turn.MoveCreaturesAction;
import simulation.entity.creature.Herbivore;
import simulation.map.GameMap;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Simulation {

    private final GameMap map;
    private final TargetSearcher searcher;
    private final Renderer renderer;

    private int simulationStepCounter;

    private final Action populateMapAction;
    private final Action moveCreaturesAction;

    public Simulation(GameMap map) {
        this.map = Objects.requireNonNull(map, "the map was not passed to the constructor");
        this.searcher = new TargetSearcher(map);
        this.renderer = new Renderer(map);
        this.populateMapAction = new PopulateMapAction(map);
        this.moveCreaturesAction = new MoveCreaturesAction(map, searcher);
    }

    public void startSimulation() {
        populateMapAction.doAction();
        showSimulationStep();
        while (!isSimulationOver()) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextTurn();
        }
        System.out.println("simulation is over");
    }

    private void nextTurn() {
        moveCreaturesAction.doAction();
        simulationStepCounter++;
        showSimulationStep();
    }

    private void showSimulationStep() {
        System.out.println("simulation step: " + simulationStepCounter);
        renderer.render();
    }

    private boolean isSimulationOver() {
        return map.getCoordinatesByType(Herbivore.class).isEmpty();
    }
}