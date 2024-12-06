package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.HitRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.Target;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.DestructibleObject;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.persistent.embeddable.Position;

import java.util.List;

public interface GameService {

    DndCharacter moveCharacter(Long campaignId, Long characterId, List<Position> path);

    SkillCheckResponse skillCheckCharacter(Long campaignId, Long characterId, SkillCheckRequest request);

    DndCharacter damageCharacter(Long campaignId, Long characterId, DamageRequest damageRequest);

    Boolean hitByCharacter(Long campaignId, Long characterId, Long targetId, Target targetType, int d20hit, HitRequest request);

    DndCharacter giveItemCharacter(Long campaignId, Long characterId, Long itemId);

    DndCharacter takeItemCharacter(Long campaignId, Long characterId, Long itemId);

    DndCharacter equipItemCharacter(Long campaignId, Long characterId, Long handItemId);

    DndCharacter unequipItemCharacter(Long campaignId, Long characterId, Long handItemId);

    DndCharacter relocateCharacter(Long campaignId, Long characterId, Long locationId);


    Creature moveCreature(Long campaignId, Long creatureId, List<Position> path);

    SkillCheckResponse skillCheckCreature(Long campaignId, Long creatureId, SkillCheckRequest request);

    Creature damageCreature(Long campaignId, Long creatureId, DamageRequest damageRequest);

    Boolean hitByCreature(Long campaignId, Long creatureId, Long targetId, Target targetType, int d20hit, HitRequest request);

    Creature giveItemCreature(Long campaignId, Long creatureId, Long itemId);

    Creature takeItemCreature(Long campaignId, Long creatureId, Long itemId);

    Creature equipItemCreature(Long campaignId, Long creatureId, Long itemId);

    Creature unequipItemCreature(Long campaignId, Long creatureId, Long itemId);

    Creature relocateCreature(Long campaignId, Long creatureId, Long locationId);


    DestructibleObject moveDestructibleObject(Long campaignId, Long destructibleObjectId, List<Position> path);

    DestructibleObject damageDestructibleObject(Long campaignId, Long destructibleObjectId, DamageRequest damageRequest);

    DestructibleObject relocateDestructibleObject(Long campaignId, Long destructibleObjectId, Long locationId);


    Item moveItem(Long campaignId, Long itemId, Long characterId, List<Position> path);

    Item relocateItem(Long campaignId, Long itemId, Long locationId);



}
