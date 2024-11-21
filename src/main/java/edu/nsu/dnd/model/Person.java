package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;


@Getter
@Setter
public class Person extends Creature {

    private ArrayList<PersonClass> personClasses;
    private PersonDescription personDescription;

    public Person(String type, Position position, int currentHealthPoints, int maxHealthPoints, int armorClass, Map<TypeOfDamage, DamageMultiplier> resistances, Size size, Status status, ArrayList<Item> backpackItems, ArrayList<Item> handItems, Abilities abilities, Race race, int speed, ArrayList<Condition> conditions, int tempHealthPoints, ArrayList<PersonClass> personClasses, PersonDescription personDescription) {
        super(type, position, currentHealthPoints, maxHealthPoints, armorClass, resistances, size, status, backpackItems, handItems, abilities, race, speed, conditions, tempHealthPoints);
        this.personClasses = personClasses;
        this.personDescription = personDescription;
    }
}
