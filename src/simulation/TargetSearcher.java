package simulation;

import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.map.Coordinate;
import simulation.map.GameMap;

import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class TargetSearcher {

    private Queue<Coordinate> queue;
    private Set<Coordinate> alreadyVisited;
    private Map<Coordinate, Coordinate> route;
    private GameMap map;

    public TargetSearcher(GameMap map) {
        this.map = map;
    }

    public <T extends Creature> List<Coordinate> findShortestWay(Coordinate originCoordinate, T creature) {
        Optional<Coordinate> maybeCoordinate = findNearestCoordinate(originCoordinate, creature.getTargetType());
        if (maybeCoordinate.isEmpty()) {
            return Collections.emptyList();
        }
        List<Coordinate> way = new ArrayList<>();
        way.add(maybeCoordinate.get());
        Coordinate previousCoordinate = route.get(maybeCoordinate.get());
        while (Objects.nonNull(previousCoordinate)) {
            way.add(previousCoordinate);
            previousCoordinate = route.get(previousCoordinate);
        }
        return way.reversed();
    }

    private  <T extends Entity> Optional<Coordinate> findNearestCoordinate(Coordinate originCoordinate, Class<T> targetClass) {
        queue = new LinkedList<>();
        queue.add(originCoordinate);
        alreadyVisited = new HashSet<>();
        route = new HashMap<>();

        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.poll();
            Optional<Entity> maybeEntity = map.getEntity(currentCoordinate);
            if (!(currentCoordinate.equals(originCoordinate)) &&
                maybeEntity.isPresent() &&
                maybeEntity.get().getClass().equals(targetClass)) {
                return Optional.of(currentCoordinate);
            }
            alreadyVisited.add(currentCoordinate);
            List<Coordinate> neighbors = new ArrayList<>(findNeighbors(currentCoordinate, targetClass));
            neighbors.removeAll(alreadyVisited);
            neighbors.removeAll(queue);
            neighbors.forEach(neighbor -> route.put(neighbor, currentCoordinate));
            queue.addAll(neighbors);
        }
        return Optional.empty();
    }

    private <T extends Entity> List<Coordinate> findNeighbors(Coordinate coordinate, Class<T> targetClass) {
        List<Coordinate> surroundingCoordinates = map.getSurroundingCoordinates(coordinate);
        List<Coordinate> empty = surroundingCoordinates.stream().filter(e -> !map.isEntityExists(e)).toList();
        List<Coordinate> target = surroundingCoordinates.stream()
                .filter(e -> !empty.contains(e))
                .filter(e -> map.getEntity(e).get().getClass().equals(targetClass))
                .toList();
        return Stream.concat(empty.stream(), target.stream()).toList();
    }

    public GameMap getMap() {
        return map;
    }
}