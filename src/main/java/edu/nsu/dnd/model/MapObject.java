package edu.nsu.dnd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class MapObject {
    private final int id;
    private String name;
    private Position position;
}
