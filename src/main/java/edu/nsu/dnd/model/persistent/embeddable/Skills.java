package edu.nsu.dnd.model.persistent.embeddable;

import edu.nsu.dnd.model.enums.Skill;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Embeddable
@Getter
@Setter
public class Skills {
    private List<Skill> proficiencySkills;
}
