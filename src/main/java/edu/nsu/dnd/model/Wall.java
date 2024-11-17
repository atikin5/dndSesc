package edu.nsu.dnd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wall extends MapObject{
    private Boolean visible;
    private Boolean walkable;

    public Wall(String id, String type, Position position, Boolean visible, Boolean walkable) {
        super(id, type, position);
        this.visible = visible;
        this.walkable = walkable;
    }
}
