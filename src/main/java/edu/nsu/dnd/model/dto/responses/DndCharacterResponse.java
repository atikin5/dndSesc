package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.Condition;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import edu.nsu.dnd.model.persistent.embeddable.CharacterClass;
import edu.nsu.dnd.model.persistent.embeddable.CharacterDescription;
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
public class DndCharacterResponse {
    private Long id;
    private Long campaignId;
    private Long locationId;
    private String type;
    private Position position;
    private boolean operational;
    private int currentHp;
    private int maxHp;
    private int temporaryHp;
    private int armorClass;
    private Abilities abilities;
    private Map<DamageType, DamageMultiplier> damageMultipliers;
    private Size size;
    private List<Condition> conditions = new ArrayList<>();
    private List<ItemResponse> backpackItems = new ArrayList<>();
    private List<ItemResponse> equipmentItems = new ArrayList<>();
    private List<CharacterClass> characterClasses = new ArrayList<>();
    private CharacterDescription characterDescription;
    private int reviveSuccesses;
    private int reviveFailures;

    public DndCharacterResponse(DndCharacter dndCharacter) {
        id = dndCharacter.getId();
        campaignId = dndCharacter.getCampaign().getId();
        if (dndCharacter.getLocation() != null) {
            locationId = dndCharacter.getLocation().getId();
        }
        else {
            locationId = null;
        }
        type = dndCharacter.getType();
        position = dndCharacter.getPosition();
        operational = dndCharacter.isOperational();
        currentHp = dndCharacter.getCurrentHp();
        maxHp = dndCharacter.getMaxHp();
        temporaryHp = dndCharacter.getTemporaryHp();
        armorClass = dndCharacter.getArmorClass();
        abilities = dndCharacter.getAbilities();
        damageMultipliers = dndCharacter.getDamageMultipliers();
        size = dndCharacter.getSize();
        conditions = dndCharacter.getConditions();
        backpackItems = dndCharacter.getBackpackItems().stream().map(ItemResponse::new).toList();
        equipmentItems = dndCharacter.getEquippedItems().stream().map(ItemResponse::new).toList();
        characterClasses = dndCharacter.getCharacterClasses();
        characterDescription = dndCharacter.getCharacterDescription();
        reviveSuccesses = dndCharacter.getReviveSuccesses();
        reviveFailures = dndCharacter.getReviveFailures();
    }

}
