package simulation;

import simulation.action.Action;
import simulation.action.init.PopulateMapAction;
import simulation.action.turn.MoveCreaturesAction;
import simulation.entity.creature.Herbivore;
import simulation.map.GameMap;

import java.util.concurrent.TimeUnit;

public class Simulation {

    GameMap map = new GameMap();
    TargetSearcher searcher = new TargetSearcher(map);
    private Renderer renderer = new Renderer(map);
    private boolean isSimulationOver;
    private int simulationStepCounter;

    private Action populateMapAction = new PopulateMapAction(map, searcher);
    private Action moveCreaturesAction = new MoveCreaturesAction(map, searcher);

    public void startSimulation() {
        populateMapAction.doAction();
        showSimulationStep();
        while (!isSimulationOver) {
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
        updateIsSimulationOver();
    }

    private void showSimulationStep() {
        System.out.println("simulation step: " + simulationStepCounter);
        renderer.render();
    }

    private void updateIsSimulationOver() {
        isSimulationOver = map.getCoordinatesByType(Herbivore.class).isEmpty();
    }
}