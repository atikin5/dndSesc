package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.enums.Race;
import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import lombok.Data;

@Data
public class CreatureRequest {
    private String type;
    private Campaign campaign;
    private Abilities abilities;
    private Race race;

}
