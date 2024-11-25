package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.enums.Condition;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.enums.Size;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@MappedSuperclass
public abstract class Destructible extends MovableObject {

    private boolean operational = true;
    private int currentHealthPoints;
    private int maxHealthPoints;
    private int temporaryHealthPoints;
    private int armorClass;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DamageType, DamageMultiplier> damageMultipliers;

    @Enumerated(EnumType.STRING)
    private Size size;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Condition> conditions = new ArrayList<>();

    public void inflictDamage(int damage, boolean critical) {
        if (temporaryHealthPoints >= damage) {
            temporaryHealthPoints -= damage;
        }
        else {
            currentHealthPoints = currentHealthPoints - damage + temporaryHealthPoints;
            temporaryHealthPoints = 0;
            if (currentHealthPoints <= 0) {
                currentHealthPoints = 0;
                operational = false;
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
