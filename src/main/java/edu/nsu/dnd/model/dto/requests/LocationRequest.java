package edu.nsu.dnd.model.dto.requests;

import lombok.Data;

@Data
public class LocationRequest {
    private String name;
    private Long campaignId;
}
