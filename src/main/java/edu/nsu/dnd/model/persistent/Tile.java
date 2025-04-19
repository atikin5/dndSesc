package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.persistent.base.LocationObject;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tile extends LocationObject {

    /**
     * <ul>
     * <li>null - никогда не видели</li>
     * <li>false - видели, но сейчас не видят (туман войны)</li>
     * <li>true - видят прямо сейчас</li>
     * </ul>
     */
    private Boolean visibleByCharacter;
    private Boolean walkable;
    private Boolean visible;
}
