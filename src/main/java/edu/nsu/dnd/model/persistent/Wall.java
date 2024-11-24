package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.enums.Orientation;
import edu.nsu.dnd.model.persistent.base.ModifiableObject;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wall extends ModifiableObject {

    private Boolean visible;
    private Boolean walkable;

    @Enumerated(EnumType.STRING)
    private Orientation orientation;
}
