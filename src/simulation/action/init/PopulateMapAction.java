package simulation.action.init;

import simulation.TargetSearcher;
import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.entity.creature.Herbivore;
import simulation.entity.creature.Predator;
import simulation.entity.fixed.Rock;
import simulation.entity.fixed.Tree;
import simulation.map.Coordinate;
import simulation.map.GameMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PopulateMapAction extends InitAction {

    private static final double MAP_LOAD_FACTOR = 0.7d;

    public PopulateMapAction(GameMap map, TargetSearcher searcher) {
        super(map, searcher);
    }

    @Override
    public void doAction() {
        int entitiesQuantity = calculateEntitiesQuantity();
        Map<Integer, Integer> entitiesQuantityByType = getEntitiesQuantityByType(entitiesQuantity);
        entitiesQuantityByType.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                Entity entity = switch (k) {
                    case 1 -> new Predator();
                    case 2 -> new Herbivore();
                    case 3 -> new Grass();
                    case 4 -> new Rock();
                    case 5 -> new Tree();
                    default -> throw new IllegalArgumentException();
                };
                map.putEntity(getCoordinate(), entity);
            }
        });
    }

    private Map<Integer, Integer> getEntitiesQuantityByType(int entitiesQuantity) {
        Map<Integer, Integer> map = new HashMap<>();

        int predatorQuantity = entitiesQuantity / 20;
        map.put(1, predatorQuantity);

        int herbivoreQuantity = entitiesQuantity / 10;
        map.put(2, herbivoreQuantity);

        int grassQuantity = entitiesQuantity / 10 * 6;
        map.put(3, grassQuantity);

        int rockQuantity = entitiesQuantity / 10;
        map.put(4, rockQuantity);

        int treeQuantity = entitiesQuantity / 10;
        map.put(5, treeQuantity);

        return map;
    }

    private int calculateEntitiesQuantity() {
        double mapArea = Math.pow(GameMap.MAP_SIZE, 2);
        return (int) (mapArea * MAP_LOAD_FACTOR);
    }

    private Coordinate getCoordinate() {
        Random random = new Random();
        Coordinate coordinate = null;
        do {
            int x = random.nextInt(GameMap.START_COORDINATE, GameMap.MAP_SIZE + 1);
            int y = random.nextInt(GameMap.START_COORDINATE, GameMap.MAP_SIZE + 1);
            coordinate = new Coordinate(x, y);
        } while (map.isEntityExists(coordinate));
        return coordinate;
    }
}