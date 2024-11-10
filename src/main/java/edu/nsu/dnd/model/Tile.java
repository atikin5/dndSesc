package edu.nsu.dnd.model;

public class Tile {

    private boolean visible;
    private boolean walkable;
    private String type;
    private int absX;
    private int absY;

    public Tile(boolean visible, boolean walkable,  String type, int absX, int absY) {
        this.visible = visible;
        this.walkable = walkable;
        this.type = type;
        this.absX = absX;
        this.absY = absY;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isWalkable() {
        return walkable;
    }


}
