package simulation.render;

import simulation.entity.Entity;
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
                if (!map.isEntityExists(coordinate)) {
                    System.out.print(PLACEHOLDER);
                } else {
                    Entity entity = map.getEntity(coordinate).get();
                    String upperCaseName = entity.getClass().getSimpleName().toUpperCase();
                    System.out.print(Sprites.valueOf(upperCaseName).getSprite());
                }
            }
            System.out.println();
        }
    }
}