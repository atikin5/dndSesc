package edu.nsu.dnd.model.persistent.embeddable;

import edu.nsu.dnd.model.enums.Background;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CharacterDescription {

    private String firstName;
    private String lastName;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Background background;
}
