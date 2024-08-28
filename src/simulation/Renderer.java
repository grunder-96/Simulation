package simulation;

import simulation.map.Coordinate;
import simulation.map.GameMap;

public class Renderer {

    private GameMap gameMap;

    public Renderer(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void render() {
        for (int i = GameMap.MAP_SIDE; i >= 1; i--) {
            for (int j = 1; j <= GameMap.MAP_SIDE; j++) {
                if (gameMap.isEntityExists(new Coordinate(j, i))) {
                    System.out.print("- ");
                    continue;
                }
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
