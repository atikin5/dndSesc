package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.enums.Orientation;
import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.model.persistent.Location;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.Data;

@Data
public class WallRequest {
    private String type;
    private Long campaignId;
    private Long locationID;
    private Position position;
    private Orientation orientation;
}
