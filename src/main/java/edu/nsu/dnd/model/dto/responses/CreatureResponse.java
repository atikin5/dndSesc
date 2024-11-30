package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.Condition;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatureResponse {
    private Long id;
    private String type;
    private Position position;
    private boolean operational;
    private int currentHp;
    private int maxHp;
    private int temporaryHp;
    private int armorClass;
    private Map<DamageType, DamageMultiplier> damageMultipliers;
    private Size size;
    private List<Condition> conditions = new ArrayList<>();
    private List<ItemResponse> backpackItems = new ArrayList<>();
    private List<ItemResponse> equipmentItems = new ArrayList<>();

    public CreatureResponse(Creature creature) {
        id = creature.getId();
        type = creature.getType();
        position = creature.getPosition();
        operational = creature.isOperational();
        currentHp = creature.getCurrentHp();
        maxHp = creature.getMaxHp();
        temporaryHp = creature.getTemporaryHp();
        armorClass = creature.getArmorClass();
        damageMultipliers = creature.getDamageMultipliers();
        size = creature.getSize();
        conditions = creature.getConditions();
        backpackItems = creature.getBackpackItems().stream().map(ItemResponse::new).toList();
        equipmentItems = creature.getEquippedItems().stream().map(ItemResponse::new).toList();
    }
}
