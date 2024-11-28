package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.enums.Orientation;
import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.model.persistent.Location;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.Data;

@Data
public class DoorRequest {
    private String type;
    private Campaign campaign;
    private Location location;
    private Position position;
    private Orientation orientation;
}
