package simulation;

import simulation.action.Action;
import simulation.action.init.PopulateMapAction;
import simulation.action.turn.MoveCreaturesAction;
import simulation.entity.creature.Herbivore;
import simulation.map.GameMap;

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
        while (!isSimulationOver) {
            nextTurn();
        }
        showLastIteration();
    }

    private void showLastIteration() {
        System.out.println("current step: " + simulationStepCounter);
        renderer.render();
        System.out.println("Симуляция завершена");
    }

    private void nextTurn() {
        System.out.println("current step: " + simulationStepCounter);
        renderer.render();
        moveCreaturesAction.doAction();
        simulationStepCounter++;
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        updateIsSimulationOver();
    }

    private void updateIsSimulationOver() {
        isSimulationOver = map.getCoordinatesByType(Herbivore.class).isEmpty();
    }
}