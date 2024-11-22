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
    private int successes;
    private int failures;

    public Person(String type, Position position, Status status, int currentHealthPoints, int maxHealthPoints, int temporaryHealthPoints, int armorClass, Map<TypeOfDamage, DamageMultiplier> damageMultipliers, Size size, ArrayList<Item> backpackItems, ArrayList<Item> handItems, Abilities abilities, Race race, int speed, ArrayList<Condition> conditions, ArrayList<PersonClass> personClasses, PersonDescription personDescription) {
        super(type, position, status, currentHealthPoints, maxHealthPoints, temporaryHealthPoints, armorClass, damageMultipliers, size, backpackItems, handItems, abilities, race, speed, conditions);
        this.personClasses = personClasses;
        this.personDescription = personDescription;
    }

    @Override
    public void inflictDamage(int damage, boolean critical) {
        if (getStatus() == Status.UNCONSCIOUS) {
            if (getMaxHealthPoints() <= damage) {
                setStatus(Status.DEAD);
            }
            else {
                if (critical) {
                    failures += 2;
                }
                else {
                    failures += 1;
                }
                if (failures >= 3) {
                    setStatus(Status.DEAD);
                }
            }
        }
        if (getStatus() == Status.ALIVE) {
            if (getTemporaryHealthPoints() >= damage) {
                setTemporaryHealthPoints(getTemporaryHealthPoints() - damage);
            } else {
                setCurrentHealthPoints(getCurrentHealthPoints() - damage + getTemporaryHealthPoints());
                setTemporaryHealthPoints(0);
                if (getCurrentHealthPoints() <= 0) {
                    setCurrentHealthPoints(0);
                    setStatus(Status.UNCONSCIOUS);
                }
            }
        }
    }

}
