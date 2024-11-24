package edu.nsu.dnd.model.persistent.embeddable;

import edu.nsu.dnd.model.enums.ClassType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CharacterClass {

    private int level;

    @Enumerated(EnumType.STRING)
    private ClassType type;

}
