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

    DndCharacter moveCharacter(Long characterId, List<Position> path);

    SkillCheckResponse skillCheckCharacter(Long characterId, SkillCheckRequest request);

    DndCharacter damageCharacter(Long characterId, DamageRequest damageRequest);

    Boolean hitByCharacter(Long characterId, Long targetId, Target targetType, int d20hit, HitRequest request);

    DndCharacter giveItemCharacter(Long characterId, Long itemId);

    DndCharacter takeItemCharacter(Long characterId, Long itemId);

    DndCharacter equipItemCharacter(Long characterId, Long handItemId);

    DndCharacter unequipItemCharacter(Long characterId, Long handItemId);

    DndCharacter relocateCharacter(Long characterId, Long locationId);


    Creature moveCreature(Long creatureId, List<Position> path);

    SkillCheckResponse skillCheckCreature(Long creatureId, SkillCheckRequest request);

    Creature damageCreature(Long creatureId, DamageRequest damageRequest);

    Boolean hitByCreature(Long creatureId, Long targetId, Target targetType, int d20hit, HitRequest request);

    Creature giveItemCreature(Long creatureId, Long itemId);

    Creature takeItemCreature(Long creatureId, Long itemId);

    Creature equipItemCreature(Long creatureId, Long itemId);

    Creature unequipItemCreature(Long creatureId, Long itemId);

    Creature relocateCreature(Long creatureId, Long locationId);


    DestructibleObject moveDestructibleObject(Long destructibleObjectId, List<Position> path);

    DestructibleObject damageDestructibleObject(Long destructibleObjectId, DamageRequest damageRequest);

    DestructibleObject relocateDestructibleObject(Long destructibleObjectId, Long locationId);


    Item moveItem(Long itemId, List<Position> path);

    Item relocateItem(Long itemId, Long locationId);

}
