package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.TypeOfPersonClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonClass {

    private int level;
    private final TypeOfPersonClass personClass;

}
