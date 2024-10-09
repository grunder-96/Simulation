package simulation;

import simulation.entity.Entity;
import simulation.map.Axises;
import simulation.map.Coordinate;
import simulation.map.GameMap;

import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TargetSearcher {

    private Queue<Coordinate> queue;
    private Set<Coordinate> alreadyVisited;
    private Map<Coordinate, Coordinate> previousCoordinateMap;
    private GameMap map;

    public TargetSearcher(GameMap map) {
        this.map = map;
    }

    public <T extends Entity> List<Coordinate> findShortestWay(Coordinate coordinate, Class<T> targetClass) {
        Optional<Coordinate> maybeCoordinate = findNearestCoordinate(coordinate, targetClass);
        return maybeCoordinate.isEmpty() ?
                Collections.emptyList() : constructWay(maybeCoordinate.get());
    }

    private List<Coordinate> constructWay(Coordinate coordinate) {
        List<Coordinate> way = new ArrayList<>();
        way.add(coordinate);
        Coordinate previousCoordinate = previousCoordinateMap.get(coordinate);
        while (Objects.nonNull(previousCoordinate)) {
            way.add(previousCoordinate);
            previousCoordinate = previousCoordinateMap.get(previousCoordinate);
        }
        way.removeLast();
        return way.reversed();
    }

    private  <T extends Entity> Optional<Coordinate> findNearestCoordinate(Coordinate coordinate, Class<T> targetClass) {
        resetFindVariables();
        queue.add(coordinate);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.poll();
            Optional<Entity> maybeEntity = map.getEntity(currentCoordinate);
            if (!(currentCoordinate.equals(coordinate)) &&
                maybeEntity.isPresent() &&
                targetClass.isAssignableFrom(maybeEntity.get().getClass())) {
                return Optional.of(currentCoordinate);
            }
            alreadyVisited.add(currentCoordinate);
            List<Coordinate> neighbors = findNeighbors(currentCoordinate, targetClass);
            neighbors.stream()
                    .filter(Predicate.not(previousCoordinateMap::containsKey))
                    .forEach(neighbor -> {
                        previousCoordinateMap.put(neighbor, currentCoordinate);
                        queue.add(neighbor);
                    });
        }
        return Optional.empty();
    }

    private void resetFindVariables() {
        queue = new LinkedList<>();
        alreadyVisited = new HashSet<>();
        previousCoordinateMap = new HashMap<>();
    }

    private <T extends Entity> List<Coordinate> findNeighbors(Coordinate coordinate, Class<T> targetClass) {
        List<Coordinate> surroundingCoordinates = getSurroundingCoordinates(coordinate);
        return surroundingCoordinates.stream()
                .filter(c -> isValidNeighbor(c, targetClass))
                .collect(Collectors.toList());
    }

    public List<Coordinate> getSurroundingCoordinates(Coordinate coordinate) {
        List<Coordinate> surroundingCoordinates = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();

        for (int i = x - 1; i <= x + 1; i++) {
            if (!map.isWithinMapBound(i, map.xAxisSize())) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (!map.isWithinMapBound(j, map.yAxisSize()) || (x == i && y == j)) {
                    continue;
                }
                Coordinate adjacentCoordinate = new Coordinate(i, j);
                surroundingCoordinates.add(adjacentCoordinate);
            }
        }
        return surroundingCoordinates;
    }

    private <T extends Entity> boolean isValidNeighbor(Coordinate coordinate, Class<T> targetClass) {
        return !map.isEntityExists(coordinate) ||
               targetClass.isAssignableFrom(map.getEntity(coordinate).get().getClass());
    }
}