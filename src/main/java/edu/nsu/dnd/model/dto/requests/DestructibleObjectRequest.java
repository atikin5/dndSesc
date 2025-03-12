package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.persistent.embeddable.Dimensions;
import lombok.Data;

@Data
public class DestructibleObjectRequest {
    private String type;
    private Long campaignId;
    private Dimensions dimensions;
}
