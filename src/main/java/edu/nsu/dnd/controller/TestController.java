package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.responses.CampaignAllResponse;
import edu.nsu.dnd.model.enums.Condition;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.persistent.*;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import edu.nsu.dnd.model.persistent.embeddable.CharacterDescription;
import edu.nsu.dnd.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Transactional
@RestController
@RequestMapping("/test")
public class TestController {

    private final CampaignRepository campaignRepository;
    private final DestructibleObjectRepository destructibleObjectRepository;
    private final CreatureRepository creatureRepository;
    private final CharacterRepository characterRepository;
    private final ItemRepository itemRepository;

    /**
     * Тестовый метод создания кампании с вложенными объектами
     */
    @PostMapping("/campaign")
    public void createCampaign() {
        Campaign campaign = new Campaign("Test campaign");
        Campaign savedCampaign = campaignRepository.save(campaign);

        DestructibleObject destructibleObject = new DestructibleObject();
        destructibleObject.setCampaign(savedCampaign);
        destructibleObject.setType("test-destrutible-object");
        destructibleObject.setDamageMultipliers(Map.of(
                DamageType.ACID, DamageMultiplier.RESISTANCE,
                DamageType.COLD, DamageMultiplier.VULNERABILITY));
        destructibleObject.setMaxHp(50);
        destructibleObject.setConditions(List.of(Condition.BLINDED, Condition.CHARMED));
        destructibleObjectRepository.save(destructibleObject);



        Creature creature = new Creature();
        creature.setCampaign(savedCampaign);
        creature.setType("test-creature");
        creature.setDamageMultipliers(Map.of(
                DamageType.FIRE, DamageMultiplier.IMMUNITY
        ));


        Item item = new Item();
        item.setCampaign(savedCampaign);
        item.setType("Hammer");
        item.setName("Test item");
        item.setDescription("Test item description");
        item.setCommonDamage(List.of(DamageType.SLASHING, DamageType.POISON));

        creature.setBackpackItems(List.of(item));

        itemRepository.save(item);


        Abilities abilities = new Abilities();
        abilities.setCharisma(4);
        abilities.setStrength(4);
        creature.setAbilities(abilities);
        creatureRepository.save(creature);

        DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setCampaign(savedCampaign);
        dndCharacter.setType("test-dndCharacter");
        dndCharacter.setDamageMultipliers(Map.of(
                DamageType.NECROTIC, DamageMultiplier.RESISTANCE,
                DamageType.COLD, DamageMultiplier.VULNERABILITY
        ));

        CharacterDescription characterDescription = new CharacterDescription();
        characterDescription.setFirstName("Test");
        characterDescription.setLastName("DndCharacter");
        dndCharacter.setCharacterDescription(characterDescription);
        characterRepository.save(dndCharacter);
    }

    @GetMapping("/campaign/{id}")
    public CampaignAllResponse getCampaignById(@PathVariable Long id) {return new CampaignAllResponse(campaignRepository.getReferenceById(id)); }
}