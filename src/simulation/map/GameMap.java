package simulation.map;

import simulation.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

public class GameMap {

    public static final int STARTING_POINT = 1;
    private final int xAxisSize;
    private final int yAxisSize;

    private final Map<Coordinate, Entity> cells = new HashMap<>();

    public GameMap(int xAxisSize, int yAxisSize) {
        checkAxisSizes(xAxisSize, yAxisSize);
        this.xAxisSize = xAxisSize;
        this.yAxisSize = yAxisSize;
    }

    private void checkAxisSizes(int xAxisSize, int yAxisSize) {
        if (xAxisSize > 0 && yAxisSize > 0) {
            return;
        }
        throw new IllegalArgumentException("axis size(-s) must be greater than zero");
    }

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
            if (!isWithinAxisSize(i, xAxisSize)) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (!isWithinAxisSize(j, yAxisSize) || (x == i && y == j)) {
                    continue;
                }
                Coordinate adjacentCoordinate = new Coordinate(i, j);
                surroundingCoordinates.add(adjacentCoordinate);
            }
        }
        return surroundingCoordinates;
    }

    private boolean isWithinAxisSize(int axisPoint, int axisSize) {
        return axisPoint >= GameMap.STARTING_POINT && axisPoint <= axisSize;
    }

    private boolean isWithinMapBounds(Coordinate coordinate) {
        Objects.requireNonNull(coordinate, "coordinate should not be null");
        return isWithinAxisSize(coordinate.getX(), xAxisSize) &&
               isWithinAxisSize(coordinate.getY(), yAxisSize);
    }

    public int xAxisSize() {
        return xAxisSize;
    }

    public int yAxisSize() {
        return yAxisSize;
    }
}