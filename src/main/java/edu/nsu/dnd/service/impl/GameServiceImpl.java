package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.dto.requests.HitRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.Target;
import edu.nsu.dnd.model.persistent.*;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.CreatureRepository;
import edu.nsu.dnd.repository.DestructibleObjectRepository;
import edu.nsu.dnd.repository.DndCharacterRepository;
import edu.nsu.dnd.repository.ItemRepository;
import edu.nsu.dnd.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final DndCharacterRepository dndCharacterRepository;
    private final CreatureRepository creatureRepository;
    private final ItemRepository itemRepository;
    private final DestructibleObjectRepository destructibleObjectRepository;
    private final WebSocketMessagingService webSocketMessagingService;
    private final DndCharacterService dndCharacterService;
    private final CreatureService creatureService;
    private final DestructibleObjectService destructibleObjectService;
    private final ItemService itemService;
    private final LocationService locationService;

//    @Override
//    public DndCharacter moveCharacter(Long campaignId, Long characterId, List<Position> positions) {
//        Session session = sessionRepository.findById(campaignId).orElseThrow(() -> new RuntimeException("session not found"));
//        // Fetch from DB
//        // Update state
//        // Save DB
//        sessionRepository.save(session);
//        webSocketMessagingService.sendMessage(session.getCode(), Map.of("aaa", ""));
//        return null;
//    }


    @Override
    public DndCharacter moveCharacter(Long campaignId, Long characterId, List<Position> path) {
        path.forEach((position) -> {dndCharacterService.move(characterId, position);});
        return dndCharacterService.get(characterId);
    }

    @Override
    public SkillCheckResponse skillCheckCharacter(Long campaignId, Long characterId, SkillCheckRequest request) {
        return dndCharacterService.skillCheck(characterId, request);
    }

    @Override
    public DndCharacter damageCharacter(Long campaignId, Long characterId, DamageRequest damageRequest) {
        return null;
    }

    @Override
    public Boolean hitByCharacter(Long campaignId, Long characterId, Long targetId, Target targetType, int d20hit, HitRequest request) {
        int hit = creatureService.giveHit(characterId, d20hit, request.getItemId());
        switch (targetType) {
            case CREATURE -> {
                return creatureService.takeHit(targetId, hit);
            }
            case DND_CHARACTER -> {
                return dndCharacterService.takeHit(targetId, hit);
            }
            case DESTRUCTIBLE_OBJECT -> {
                return destructibleObjectService.takeHit(targetId, hit);
            }
        }
        return null;
    }

    @Override
    public DndCharacter giveItemCharacter(Long campaignId, Long characterId, Long itemId) {
        return dndCharacterService.addItem(characterId, itemId);
    }

    @Override
    public DndCharacter takeItemCharacter(Long campaignId, Long characterId, Long itemId) {
        return dndCharacterService.removeItem(characterId, itemId);
    }

    @Override
    public DndCharacter equipItemCharacter(Long campaignId, Long characterId, Long handItemId) {
        DndCharacter dndCharacter = dndCharacterService.get(characterId);
        Item item = itemService.get(handItemId);
        dndCharacter.equip(item);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter unequipItemCharacter(Long campaignId, Long characterId, Long handItemId) {
        DndCharacter dndCharacter = dndCharacterService.get(characterId);
        Item item = itemService.get(handItemId);
        dndCharacter.unequip(item);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter relocateCharacter(Long campaignId, Long characterId, Long locationId) {
        Location location = locationService.get(locationId);
        if (Objects.equals(location.getCampaign().getId(), campaignId)) {
            return dndCharacterService.relocate(characterId, locationId);
        }
        return null;
    }

    @Override
    public Creature moveCreature(Long campaignId, Long creatureId, List<Position> path) {
        path.forEach((position) -> {creatureService.move(creatureId, position);});
        return creatureService.get(creatureId);
    }

    @Override
    public SkillCheckResponse skillCheckCreature(Long campaignId, Long creatureId, SkillCheckRequest request) {
        return creatureService.skillCheck(creatureId, request);
    }

    @Override
    public Creature damageCreature(Long campaignId, Long creatureId, DamageRequest damageRequest) {
        return creatureService.damage(creatureId, damageRequest);
    }

    @Override
    public Boolean hitByCreature(Long campaignId, Long creatureId, Long targetId, Target targetType, int d20hit, HitRequest request) {
        int hit = creatureService.giveHit(creatureId, d20hit, request.getItemId());
        switch (targetType) {
            case CREATURE -> {
                return creatureService.takeHit(creatureId, hit);
            }
            case DND_CHARACTER -> {
                return dndCharacterService.takeHit(creatureId, hit);
            }
            case DESTRUCTIBLE_OBJECT -> {
                return destructibleObjectService.takeHit(creatureId, hit);
            }
        }
        return null;
    }

    @Override
    public Creature giveItemCreature(Long campaignId, Long creatureId, Long itemId) {
        return creatureService.addItem(creatureId, itemId);
    }

    @Override
    public Creature takeItemCreature(Long campaignId, Long creatureId, Long itemId) {
        return creatureService.removeItem(creatureId, itemId);
    }

    @Override
    public Creature equipItemCreature(Long campaignId, Long creatureId, Long itemId) {
        Creature creature = creatureService.get(itemId);
        Item item = itemService.get(itemId);
        creature.equip(item);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature unequipItemCreature(Long campaignId, Long creatureId, Long itemId) {
        Creature creature = creatureService.get(itemId);
        Item item = itemService.get(itemId);
        creature.unequip(item);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature relocateCreature(Long campaignId, Long creatureId, Long locationId) {
        return creatureService.relocate(creatureId, locationId);
    }

    @Override
    public DestructibleObject moveDestructibleObject(Long campaignId, Long destructibleObjectId, List<Position> path) {
        path.forEach(position -> {destructibleObjectService.move(destructibleObjectId, position);});
        return destructibleObjectService.get(destructibleObjectId);
    }

    @Override
    public DestructibleObject damageDestructibleObject(Long campaignId, Long destructibleObjectId, DamageRequest damageRequest) {
        return destructibleObjectService.damage(destructibleObjectId, damageRequest);
    }

    @Override
    public DestructibleObject relocateDestructibleObject(Long campaignId, Long destructibleObjectId, Long locationId) {
        DestructibleObject destructibleObject = destructibleObjectService.get(locationId);
        destructibleObject.setLocation(locationService.get(locationId));
        Position position = new Position();
        destructibleObject.setPosition(position);
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public Item moveItem(Long campaignId, Long itemId, List<Position> path) {
        path.forEach(position -> {itemService.move(itemId, position);});
        return itemService.get(itemId);
    }

    @Override
    public Item relocateItem(Long campaignId, Long itemId, Long locationId) {
        return itemService.relocate(itemId, locationId);
    }
}
