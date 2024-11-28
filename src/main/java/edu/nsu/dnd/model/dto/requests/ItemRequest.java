package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.persistent.Campaign;
import lombok.Data;

@Data
public class ItemRequest {
    private String type;
    private Campaign campaign;
}
