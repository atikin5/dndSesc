package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.Background;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDescription {

    private String firstName;
    private String lastName;
    private Integer age;
    private Background background;

}