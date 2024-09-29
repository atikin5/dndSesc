package edu.nsu.dnd.model;

public class Tile {

    private boolean visible;
    private boolean walkable;

    public Tile(boolean visible, boolean walkable) {
        this.visible = visible;
        this.walkable = walkable;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isWalkable() {
        return walkable;
    }


}
