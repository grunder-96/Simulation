package simulation.map;

import simulation.entity.Entity;

import java.util.*;

public class GameMap {

    public static final int MAP_SIDE = 10;

    private Map<Coordinate, Entity> cells = new HashMap<>();

    public boolean isEntityExists(Coordinate coordinate) {
        Optional<Entity> maybeEntity = Optional.ofNullable(cells.get(coordinate));
        return cells.containsKey(coordinate) && maybeEntity.isEmpty();
    }

//    public void addEntity(Coordinate coordinate, Entity entity) {
//        if (!isEntityExists(coordinate)) {
//            cells.put(coordinate, entity);
//        }
//    }
}