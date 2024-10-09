package simulation.render;

public enum Sprites {
    PREDATOR("\uD83D\uDC3A"),
    HERBIVORE("\uD83D\uDC10"),
    TREE("\uD83C\uDF33"),
    ROCK("\uD83E\uDEA8"),
    GRASS("\uD83D\uDFE9");

    private final String sprite;

    Sprites(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }
}