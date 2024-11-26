package edu.nsu.dnd.model.persistent.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ItemPosition {
    private int handPosition;
    private int fingerPosition;
    private int headPosition;
    private int bodyPosition;
    private int footPosition;
}
