package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.persistent.Campaign;
import lombok.Data;

@Data
public class LocationRequest {
    private String name;
    private Campaign campaign;
}
