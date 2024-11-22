package edu.nsu.dnd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {

    private Person[] characters;
    private Creature[] enemies;
    private InteractiveObject[] otherObjects;
    private GameMap[] gameMaps;

}
