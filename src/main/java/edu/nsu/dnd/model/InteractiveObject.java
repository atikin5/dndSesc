package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.enums.Status;
import edu.nsu.dnd.model.enums.TypeOfDamage;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InteractiveObject extends MapObject{

    private Status status;
    private int currentHealthPoints;
    private int maxHealthPoints;
    private int temporaryHealthPoints;
    private int armorClass;
    private Map<TypeOfDamage, DamageMultiplier> damageMultipliers;
    private Size size;

    public InteractiveObject(String type, Position position, Status status, int currentHealthPoints, int maxHealthPoints, int temporaryHealthPoints, int armorClass, Map<TypeOfDamage, DamageMultiplier> damageMultipliers, Size size) {
        super(type, position);
        this.status = status;
        this.currentHealthPoints = currentHealthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.temporaryHealthPoints = temporaryHealthPoints;
        this.armorClass = armorClass;
        this.damageMultipliers = damageMultipliers;
        this.size = size;
    }

    public void inflictDamage(int damage, boolean critical) {
        if (temporaryHealthPoints >= damage) {
            temporaryHealthPoints -= damage;
        }
        else {
            currentHealthPoints = currentHealthPoints - damage + temporaryHealthPoints;
            temporaryHealthPoints = 0;
            if (currentHealthPoints <= 0) {
                currentHealthPoints = 0;
                status = Status.DEAD;
            }
        }
    }

    public void healDamage(int damage) {
        currentHealthPoints += damage;
        if (currentHealthPoints >= maxHealthPoints) {
            currentHealthPoints = maxHealthPoints;
        }
    }

    public void healTemporaryDamage(int damage) {
        temporaryHealthPoints = Math.max(temporaryHealthPoints, damage);
    }

}
