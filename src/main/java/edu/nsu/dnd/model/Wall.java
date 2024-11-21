package edu.nsu.dnd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wall extends MapObject{

    private Boolean visible;
    private Boolean walkable;
    private Boolean orientation;

    public Wall(String type, Position position, Boolean visible, Boolean walkable, Boolean orientation) {
        super(type, position);
        this.visible = visible;
        this.walkable = walkable;
        this.orientation = orientation;
    }
}
