package edu.nsu.dnd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile extends MapObject{

    private Boolean visibleAtMoment;

    public Tile(String id, String type, Position position, Boolean visibleAtMoment) {
        super(id, type, position);
        this.visibleAtMoment = visibleAtMoment;
    }
}
