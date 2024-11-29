package edu.nsu.dnd.model.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCheckResponse {

    private int value;
    private Boolean checked;
}
