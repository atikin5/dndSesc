package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.Data;

@Data
public class TileRequest {
    private String type;
    private Long campaignId;
    private Long locationId;
    private Position position;
    private Boolean visibleByCharacter;
    private Boolean walkable;
    private Boolean visible;
}
