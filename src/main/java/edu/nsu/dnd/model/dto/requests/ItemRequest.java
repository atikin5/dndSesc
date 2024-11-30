package edu.nsu.dnd.model.dto.requests;

import lombok.Data;

@Data
public class ItemRequest {
    private String type;
    private Long campaignId;
}
