package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.enums.Target;
import lombok.Data;

@Data
public class HitFullRequest {
    private Long targetId;
    private Target targetType;
    private int d20hit;
    private HitRequest hitRequest;
}
