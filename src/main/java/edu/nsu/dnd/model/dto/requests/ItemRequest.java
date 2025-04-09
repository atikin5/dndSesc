package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.persistent.embeddable.ItemPosition;
import lombok.Data;

@Data
public class ItemRequest {
    private String type;
    private Long campaignId;
    private ItemPosition itemPosition;
}
