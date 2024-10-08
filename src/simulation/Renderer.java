package simulation;

import simulation.map.Coordinate;
import simulation.map.GameMap;

public class Renderer {

    private final GameMap map;
    private static final String PLACEHOLDER = "\uD83D\uDFEB";

    public Renderer(GameMap map) {
        this.map = map;
    }

    public void render() {
        for (int i = map.yAxisSize(); i >= 1; i--) {
            for (int j = 1; j <= map.xAxisSize(); j++) {
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
