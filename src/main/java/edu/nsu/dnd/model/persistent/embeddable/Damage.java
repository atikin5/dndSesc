package edu.nsu.dnd.model.persistent.embeddable;

import edu.nsu.dnd.model.enums.DamageType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Damage {
    private int damage;
    private Boolean critical;
    private DamageType damageType;

}
