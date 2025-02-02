package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.*;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import edu.nsu.dnd.model.persistent.embeddable.ItemPosition;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatureResponse {
    private Long id;
    private Long campaignId;
    private Long locationId;
    private String type;
    private Position position;
    private boolean operational;
    private ItemPosition maxItemPosition;
    private int currentHp;
    private int maxHp;
    private int temporaryHp;
    private int armorClass;
    private Map<DamageType, DamageMultiplier> damageMultipliers;
    private Size size;
    private Abilities abilities;
    private Race race;
    private List<Condition> conditions = new ArrayList<>();
    private List<ItemResponse> backpackItems = new ArrayList<>();
    private List<ItemResponse> equippedItems = new ArrayList<>();

    public CreatureResponse(Creature creature) {
        id = creature.getId();
        campaignId = creature.getCampaign().getId();
        if (creature.getLocation() != null) {
            locationId = creature.getLocation().getId();
        }
        else {
            locationId = null;
        }
        type = creature.getType();
        position = creature.getPosition();
        operational = creature.isOperational();
        maxItemPosition = creature.getMaxItemPosition();
        currentHp = creature.getCurrentHp();
        maxHp = creature.getMaxHp();
        temporaryHp = creature.getTemporaryHp();
        armorClass = creature.getArmorClass();
        damageMultipliers = creature.getDamageMultipliers();
        size = creature.getSize();
        conditions = creature.getConditions();
        abilities = creature.getAbilities();
        race = creature.getRace();
        backpackItems = creature.getBackpackItems().stream().map(ItemResponse::new).toList();
        equippedItems = creature.getEquippedItems().stream().map(ItemResponse::new).toList();
    }
}
