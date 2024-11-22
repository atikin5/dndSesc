package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
public class Creature extends InteractiveObject {

    private ArrayList<Item> backpackItems;
    private ArrayList<Item> handItems;
    private Abilities abilities;
    private Race race;
    private int speed;
    private ArrayList<Condition> conditions;

    public Creature(String type, Position position, Status status, int currentHealthPoints, int maxHealthPoints, int temporaryHealthPoints, int armorClass, Map<TypeOfDamage, DamageMultiplier> damageMultipliers, Size size, ArrayList<Item> backpackItems, ArrayList<Item> handItems, Abilities abilities, Race race, int speed, ArrayList<Condition> conditions) {
        super(type, position, status, currentHealthPoints, maxHealthPoints, temporaryHealthPoints, armorClass, damageMultipliers, size);
        this.backpackItems = backpackItems;
        this.handItems = handItems;
        this.abilities = abilities;
        this.race = race;
        this.speed = speed;
        this.conditions = conditions;
    }
}
