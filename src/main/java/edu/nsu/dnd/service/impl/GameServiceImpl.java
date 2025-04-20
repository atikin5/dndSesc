package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.dto.requests.HitRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.Target;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.DestructibleObject;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.Location;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.CreatureRepository;
import edu.nsu.dnd.repository.DestructibleObjectRepository;
import edu.nsu.dnd.repository.DndCharacterRepository;
import edu.nsu.dnd.repository.ItemRepository;
import edu.nsu.dnd.service.CreatureService;
import edu.nsu.dnd.service.DestructibleObjectService;
import edu.nsu.dnd.service.DndCharacterService;
import edu.nsu.dnd.service.GameService;
import edu.nsu.dnd.service.ItemService;
import edu.nsu.dnd.service.LocationService;
import edu.nsu.dnd.service.WebSocketMessagingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public DndCharacter moveCharacter(Long characterId, List<Position> path) {
        path.forEach((position) -> {
            dndCharacterService.move(characterId, position);
        });
        return dndCharacterService.get(characterId);
    }

    @Override
    public SkillCheckResponse skillCheckCharacter(Long characterId, SkillCheckRequest request) {
        return dndCharacterService.skillCheck(characterId, request);
    }

    @Override
    public DndCharacter damageCharacter(Long characterId, DamageRequest damageRequest) {
        return null;
    }

    @Override
    public Boolean hitByCharacter(Long characterId, Long targetId, Target targetType, int d20hit, HitRequest request) {
        int hit = creatureService.giveHit(characterId, d20hit, request.getItemId());
        return switch (targetType) {
            case CREATURE -> creatureService.takeHit(targetId, hit);
            case DND_CHARACTER -> dndCharacterService.takeHit(targetId, hit);
            case DESTRUCTIBLE_OBJECT -> destructibleObjectService.takeHit(targetId, hit);
        };
    }

    @Override
    public DndCharacter giveItemCharacter(Long characterId, Long itemId) {
        return dndCharacterService.addItem(characterId, itemId);
    }

    @Override
    public DndCharacter takeItemCharacter(Long characterId, Long itemId) {
        return dndCharacterService.removeItem(characterId, itemId);
    }

    @Override
    public DndCharacter equipItemCharacter(Long characterId, Long handItemId) {
        DndCharacter dndCharacter = dndCharacterService.get(characterId);
        Item item = itemService.get(handItemId);
        dndCharacter.equip(item);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter unequipItemCharacter(Long characterId, Long handItemId) {
        DndCharacter dndCharacter = dndCharacterService.get(characterId);
        Item item = itemService.get(handItemId);
        dndCharacter.unequip(item);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter relocateCharacter(Long characterId, Long locationId) {
        Location location = locationService.get(locationId);
        return dndCharacterService.relocate(characterId, locationId);
    }

    @Override
    public List<Position> moveCreature(Long creatureId, List<Position> path) {
        for (int i = 1; i < path.size(); i++) {
            Position newPosition = path.get(i);
            Position oldPosition = path.get(i - 1);
            if (Math.abs(newPosition.getX() - oldPosition.getX()) < 2 && Math.abs(newPosition.getY() - oldPosition.getY()) < 2) {
            }
        }
        Creature creature = creatureService.get(creatureId);
        creature.setPosition(path.getLast());
        webSocketMessagingService.sendMessage(creature.getLocation().getId().toString(), path);
        return path;
    }

    @Override
    public SkillCheckResponse skillCheckCreature(Long creatureId, SkillCheckRequest request) {
        return creatureService.skillCheck(creatureId, request);
    }

    @Override
    public Creature damageCreature(Long creatureId, DamageRequest damageRequest) {
        return creatureService.damage(creatureId, damageRequest);
    }

    @Override
    public Boolean hitByCreature(Long creatureId, Long targetId, Target targetType, int d20hit, HitRequest request) {
        int hit = creatureService.giveHit(creatureId, d20hit, request.getItemId());
        return switch (targetType) {
            case CREATURE -> creatureService.takeHit(creatureId, hit);
            case DND_CHARACTER -> dndCharacterService.takeHit(creatureId, hit);
            case DESTRUCTIBLE_OBJECT -> destructibleObjectService.takeHit(creatureId, hit);
        };
    }

    @Override
    public Creature giveItemCreature(Long creatureId, Long itemId) {
        return creatureService.addItem(creatureId, itemId);
    }

    @Override
    public Creature takeItemCreature(Long creatureId, Long itemId) {
        return creatureService.removeItem(creatureId, itemId);
    }

    @Override
    public Creature equipItemCreature(Long creatureId, Long itemId) {
        Creature creature = creatureService.get(itemId);
        Item item = itemService.get(itemId);
        creature.equip(item);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature unequipItemCreature(Long creatureId, Long itemId) {
        Creature creature = creatureService.get(itemId);
        Item item = itemService.get(itemId);
        creature.unequip(item);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature relocateCreature(Long creatureId, Long locationId) {
        return creatureService.relocate(creatureId, locationId);
    }

    @Override
    public DestructibleObject moveDestructibleObject(Long destructibleObjectId, List<Position> path) {
        path.forEach(position -> {
            destructibleObjectService.move(destructibleObjectId, position);
        });
        return destructibleObjectService.get(destructibleObjectId);
    }

    @Override
    public DestructibleObject damageDestructibleObject(Long destructibleObjectId, DamageRequest damageRequest) {
        return destructibleObjectService.damage(destructibleObjectId, damageRequest);
    }

    @Override
    public DestructibleObject relocateDestructibleObject(Long destructibleObjectId, Long locationId) {
        DestructibleObject destructibleObject = destructibleObjectService.get(locationId);
        destructibleObject.setLocation(locationService.get(locationId));
        Position position = new Position();
        destructibleObject.setPosition(position);
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public Item moveItem(Long itemId, List<Position> path) {
        path.forEach(position -> {
            itemService.move(itemId, position);
        });
        return itemService.get(itemId);
    }

    @Override
    public Item relocateItem(Long itemId, Long locationId) {
        return itemService.relocate(itemId, locationId);
    }
}
