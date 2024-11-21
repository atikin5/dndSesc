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

    public Creature(String type, Position position, int currentHealthPoints, int maxHealthPoints, int armorClass, Map<TypeOfDamage, DamageMultiplier> resistances, Size size, Status status, ArrayList<Item> backpackItems, ArrayList<Item> handItems, Abilities abilities, Race race, int speed, ArrayList<Condition> conditions, int tempHealthPoints) {
        super(type, position, currentHealthPoints, maxHealthPoints, armorClass, resistances, size);
        this.status = status;
        this.backpackItems = backpackItems;
        this.handItems = handItems;
        this.abilities = abilities;
        this.race = race;
        this.speed = speed;
        this.conditions = conditions;
        this.tempHealthPoints = tempHealthPoints;
    }
}
