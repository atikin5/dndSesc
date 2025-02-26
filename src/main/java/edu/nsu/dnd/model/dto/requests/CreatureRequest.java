package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.enums.*;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.model.persistent.embeddable.Skills;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatureRequest {
    private Long campaignId;
    private Long locationId;
    private String type;
    private Position position;
    private boolean operational;
    private int currentHp;
    private int maxHp;
    private int temporaryHp;
    private int armorClass;
    private Map<DamageType, DamageMultiplier> damageMultipliers;
    private Size size;
    private Abilities abilities;
    private Race race;
    private List<Skill> skills = new ArrayList<>();
    private List<Condition> conditions = new ArrayList<>();
    private List<Long> BackpackItemIds = new ArrayList<>();
    private List<Long> EquippedItemIds = new ArrayList<>();

}
