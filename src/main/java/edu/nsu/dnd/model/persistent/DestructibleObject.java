package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.persistent.base.Destructible;
import edu.nsu.dnd.model.persistent.embeddable.Dimensions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DestructibleObject extends Destructible {
    private Dimensions dimensions;

}
