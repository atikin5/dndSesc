package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.enums.Ability;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.persistent.base.MovableObject;
import edu.nsu.dnd.model.persistent.embeddable.ItemPosition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Носимый предмет.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item extends MovableObject {

    private String name;
    private String description;
    private ItemPosition itemPosition;
    private boolean melee;
    private int bonus;
    private Ability attackAbility;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DamageType> commonDamage = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DamageType> optionalDamage = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DamageType> missDamage = new ArrayList<>();
}
