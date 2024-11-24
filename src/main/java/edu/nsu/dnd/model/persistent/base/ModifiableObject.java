package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Объект, который можно поменять в рамках локации (дверь, разрушаемая стена)
 */
@Setter
@Getter
@MappedSuperclass
public abstract class ModifiableObject extends LocationObject {

    private boolean operational = true;
    private int currentHp;
    private int maxHp;
    private int armorClass;
    private int durability;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DamageType, DamageMultiplier> damageMultipliers = new HashMap<>();
}
