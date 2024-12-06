package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.enums.DamageType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DamageRequest {

    private int damage;
    private Boolean critical;
    private DamageType damageType;

}
