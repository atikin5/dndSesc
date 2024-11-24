package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.enums.Condition;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.DestructibleObject;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import edu.nsu.dnd.model.persistent.embeddable.CharacterDescription;
import edu.nsu.dnd.repository.CampaignRepository;
import edu.nsu.dnd.repository.CharacterRepository;
import edu.nsu.dnd.repository.CreatureRepository;
import edu.nsu.dnd.repository.DestructibleObjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@RestController
@RequestMapping("/test")
public class TestController {

    private final CampaignRepository campaignRepository;
    private final DestructibleObjectRepository destructibleObjectRepository;
    private final CreatureRepository creatureRepository;
    private final CharacterRepository characterRepository;

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
        destructibleObject.setMaxHealthPoints(50);
        destructibleObject.setConditions(List.of(Condition.BLINDED, Condition.CHARMED));
        destructibleObjectRepository.save(destructibleObject);

        Creature creature = new Creature();
        creature.setCampaign(savedCampaign);
        creature.setType("test-creature");

        Abilities abilities = new Abilities();
        abilities.setCharisma(4);
        abilities.setStrength(4);
        creature.setAbilities(abilities);
        creatureRepository.save(creature);

        DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setCampaign(savedCampaign);
        dndCharacter.setType("test-dndCharacter");

        CharacterDescription characterDescription = new CharacterDescription();
        characterDescription.setFirstName("Test");
        characterDescription.setLastName("DndCharacter");
        dndCharacter.setCharacterDescription(characterDescription);
        creatureRepository.save(dndCharacter);
    }

    @GetMapping("/campaign")
    public Campaign getCampaign() {
        //Campaign campaign = campaignRepository.findAll().getFirst();

        Optional<DestructibleObject> destructibleObject = destructibleObjectRepository.findById(1L);
        Optional<Creature> creature2 = creatureRepository.findById(2L);
//        Optional<Creature> creature3 = creatureRepository.findById(3L);
        Optional<DndCharacter> character = characterRepository.findById(3L);
        //System.out.printf("Found characters:  " + campaign.getDndCharacters().size());
        return null;
    }


        //    @GetMapping("/hello/{name}/{age}")
//    public DndCharacter helloGet(@PathVariable String name, @PathVariable int age) {
////        DndCharacter person = new DndCharacter
////        person.setName(name);
////        person.setAge(age);
////        return person;
//    }
}