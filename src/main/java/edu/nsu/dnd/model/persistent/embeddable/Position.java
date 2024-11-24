package edu.nsu.dnd.model.persistent.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Position {

    private Integer x;
    private Integer y;
}
