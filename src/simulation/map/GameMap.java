package simulation.map;

import simulation.entity.Entity;

import java.util.*;

public class GameMap {

    public static final int START_COORDINATE = 1;
    public static final int MAP_SIZE = 10;

    private Map<Coordinate, Entity> cells = new HashMap<>();

    public boolean isEntityExists(Coordinate coordinate) {
        return getEntity(coordinate).isPresent();
    }

    public void putEntity(Coordinate coordinate, Entity entity) {
        cells.put(coordinate, entity);
    }

    public Optional<Entity> getEntity(Coordinate coordinate) {
        return Optional.ofNullable(cells.get(coordinate));
    }
}