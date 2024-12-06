package edu.nsu.dnd.model.dto.requests;

import lombok.Data;

@Data
public class HitRequest {
    private boolean skillCheck;
    private int distance;
    private boolean melee;
    private Long itemId;
}
