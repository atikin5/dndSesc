package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.Resistance;
import edu.nsu.dnd.model.enums.TypeOfDamage;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class Person extends Creature {

    private int age;
    private PersonClass personClass;

    public Person(String id, String name, Position position, int healthPoints, int armorClass, Map<TypeOfDamage, Resistance> resistances) {
        super(id, name, position, healthPoints, armorClass, resistances);
    }
}
