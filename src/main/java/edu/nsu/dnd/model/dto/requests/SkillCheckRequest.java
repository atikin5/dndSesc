package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.enums.Ability;
import edu.nsu.dnd.model.enums.Skill;
import lombok.Data;

@Data
public class SkillCheckRequest {

    private Ability ability;
    private Skill skill;
    private float proficiencyBonusMultiplier;
    private int d20Value;
}
