package edu.nsu.dnd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile extends MapObject{

    private Boolean visibleAtMoment;

    public Tile(String type, Position position, Boolean visibleAtMoment) {
        super(type, position);
        this.visibleAtMoment = visibleAtMoment;
    }
}
