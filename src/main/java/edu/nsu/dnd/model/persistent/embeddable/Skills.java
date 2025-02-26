package edu.nsu.dnd.model.persistent.embeddable;

import edu.nsu.dnd.model.enums.Skill;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    private List<Skill> proficiencySkills;
}
