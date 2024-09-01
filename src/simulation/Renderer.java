package simulation;

import simulation.map.Coordinate;
import simulation.map.GameMap;

public class Renderer {

    private GameMap map;
    private static final String PLACEHOLDER = "\uD83D\uDFEB";

    public Renderer(GameMap map) {
        this.map = map;
    }

    public void render() {
        for (int i = GameMap.MAP_SIZE; i >= 1; i--) {
            for (int j = 1; j <= GameMap.MAP_SIZE; j++) {
                Coordinate coordinate = new Coordinate(j, i);
                if (map.isEntityExists(coordinate)) {
                    System.out.print(map.getEntity(coordinate).get() + " ");
                    continue;
                }
                System.out.print(PLACEHOLDER + " ");
            }
            System.out.println();
        }
    }
}
