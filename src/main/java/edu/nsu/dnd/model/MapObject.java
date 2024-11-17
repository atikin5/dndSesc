package edu.nsu.dnd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MapObject {

    private final String id;
    private String type;
    private Position position;

}
