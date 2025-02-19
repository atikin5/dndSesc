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

    /**
     * "Живо" или нет
     */
    private boolean operational = true;
    private int currentHp;
    private int maxHp;
    private int temporaryHp;
    private int armorClass;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DamageType, DamageMultiplier> damageMultipliers;

    @Enumerated(EnumType.STRING)
    private Size size = Size.MEDIUM;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Condition> conditions = new ArrayList<>();

    public void inflictDamage(int damage, boolean critical) {
        if (temporaryHp >= damage) {
            temporaryHp -= damage;
        }
        else {
            currentHp = currentHp - damage + temporaryHp;
            temporaryHp = 0;
            if (currentHp <= 0) {
                currentHp = 0;
                operational = false;
            }
        }
    }

    public void healDamage(int damage) {
        currentHp += damage;
        if (currentHp >= maxHp) {
            currentHp = maxHp;
        }
    }

    public void healTemporaryDamage(int damage) {
        temporaryHp = Math.max(temporaryHp, damage);
    }

}
