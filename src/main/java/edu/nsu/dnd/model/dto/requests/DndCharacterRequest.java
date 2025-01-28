package edu.nsu.dnd.model.dto.requests;

import edu.nsu.dnd.model.dto.responses.ItemResponse;
import edu.nsu.dnd.model.enums.*;
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
public class DndCharacterRequest {
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
    private Race race;
    private List<Condition> conditions = new ArrayList<>();
    private List<CharacterClass> characterClasses = new ArrayList<>();
    private CharacterDescription characterDescription;
    private int reviveSuccesses;
    private int reviveFailures;

    public DndCharacterRequest(DndCharacter dndCharacter) {
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
        race = dndCharacter.getRace();
        conditions = dndCharacter.getConditions();
        characterClasses = dndCharacter.getCharacterClasses();
        characterDescription = dndCharacter.getCharacterDescription();
        reviveSuccesses = dndCharacter.getReviveSuccesses();
        reviveFailures = dndCharacter.getReviveFailures();
    }
}
