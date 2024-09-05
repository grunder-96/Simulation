package simulation.map;

import simulation.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

public class GameMap {

    public static final int START_COORDINATE = 1;
    public static final int MAP_SIZE = 15;

    private Map<Coordinate, Entity> cells = new HashMap<>();

    public boolean isEntityExists(Coordinate coordinate) {
        return getEntity(coordinate).isPresent();
    }

    public void putEntity(Coordinate coordinate, Entity entity) {
        cells.put(coordinate, entity);
    }

    public void removeEntity(Coordinate coordinate) {
        cells.remove(coordinate);
    }

    public Optional<Entity> getEntity(Coordinate coordinate) {
        return Optional.ofNullable(cells.get(coordinate));
    }

    public Set<Coordinate> getCoordinatesByType(Class<? extends Entity> clazz) {
        return cells.keySet().stream()
                .filter(key -> Objects.nonNull(cells.get(key)))
                .filter(key -> clazz.isInstance(cells.get(key)))
                .collect(Collectors.toSet());
    }

    public List<Coordinate> getSurroundingCoordinates(Coordinate coordinate) {
        List<Coordinate> surroundingCoordinates = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();

        for (int i = x - 1; i <= x + 1; i++) {
            if (!isWithinMapBounds(i)) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (!isWithinMapBounds(j) || (x == i && y == j)) {
                    continue;
                }
                Coordinate adjacentCoordinate = new Coordinate(i, j);
                surroundingCoordinates.add(adjacentCoordinate);
            }
        }
        return surroundingCoordinates;
    }

    private boolean isWithinMapBounds(int i) {
        return i >= GameMap.START_COORDINATE && i <= GameMap.MAP_SIZE;
    }
}