package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
public class Creature extends InteractiveObject {

    private Status status;
    private ArrayList<Item> backpackItems;
    private ArrayList<Item> handItems;
    private Abilities abilities;
    private Race race;
    private int speed;
    private ArrayList<Condition> conditions;
    private int tempHealthPoints;

    public Creature(String id, String name, Position position, int healthPoints, int armorClass, Map<TypeOfDamage, Resistance> resistances) {
        super(id, name, position, healthPoints, armorClass, resistances);
    }
}
