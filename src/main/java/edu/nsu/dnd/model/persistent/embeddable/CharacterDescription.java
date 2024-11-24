package edu.nsu.dnd.model.persistent.embeddable;

import edu.nsu.dnd.model.enums.Background;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CharacterDescription {

    private String firstName;
    private String lastName;
    private Integer age;
    private Background background;

}