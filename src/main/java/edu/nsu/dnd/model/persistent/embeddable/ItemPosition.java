package edu.nsu.dnd.model.persistent.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPosition {

    private int handPosition;
    private int fingerPosition;
    private int headPosition;
    private int bodyPosition;
    private int footPosition;
    private int cloakPosition;
    private int legsPosition;
    private int neckPosition;

}
