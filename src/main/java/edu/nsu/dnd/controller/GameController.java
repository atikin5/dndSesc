package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.dto.requests.HitFullRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.*;
import edu.nsu.dnd.model.enums.Target;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.service.GameService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @PostMapping("/character/{id}/move")
    public DndCharacterResponse moveDndCharacter(@PathVariable Long id, @RequestBody List<Position> path) {
        return new DndCharacterResponse(gameService.moveCharacter(id, path));
    }

    @PostMapping("/character/{id}/skill_check")
    public SkillCheckResponse skillCheckDndCharacter(@PathVariable Long id, @RequestBody SkillCheckRequest request) {
        return gameService.skillCheckCharacter(id, request);
    }

    @PostMapping("/character/{id}/damage")
    public DndCharacterResponse damageDndCharacter(@PathVariable Long id, @RequestBody DamageRequest request) {
        return new DndCharacterResponse(gameService.damageCharacter(id, request));
    }

    @PostMapping("/character/{id}/hit")
    public Boolean hitByDndCharacter(@PathVariable Long id, @RequestBody HitFullRequest request) {
        return gameService.hitByCharacter(id, request.getTargetId(), request.getTargetType(), request.getD20hit(), request.getHitRequest());
    }

    @PostMapping("/character/{id}/give_item")
    public DndCharacterResponse giveItemToDndCharacter(@PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.giveItemCharacter(id, itemId));
    }

    @PostMapping("/character/{id}/take_item")
    public DndCharacterResponse takeItemFromDndCharacter(@PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.takeItemCharacter(id, itemId));
    }

    @PostMapping("/character/{id}/equip_item")
    public DndCharacterResponse equipItemToDndCharacter(@PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.equipItemCharacter(id, itemId));
    }

    @PostMapping("/character/{id}/unequip_item")
    public DndCharacterResponse unequipItemToDndCharacter(@PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.unequipItemCharacter(id, itemId));
    }

    @PostMapping("/character/{id}/relocate")
    public DndCharacterResponse relocateDndCharacter(@PathVariable Long id, @RequestBody Long locationId) {
        return new DndCharacterResponse(gameService.relocateCharacter(id, locationId));
    }

    @PostMapping("/creature/{id}/move")
    public List<Position> moveCreature(@PathVariable Long id, @RequestBody List<Position> path) {
        return gameService.moveCreature(id, path);
    }

    @PostMapping("/creature/{id}/skill_check")
    public SkillCheckResponse skillCheckCreature(@PathVariable Long id, @RequestBody SkillCheckRequest request) {
        return gameService.skillCheckCreature(id, request);
    }

    @PostMapping("/creature/{id}/damage")
    public CreatureResponse damageCreature(@PathVariable Long id, @RequestBody DamageRequest request) {
        return new CreatureResponse(gameService.damageCreature(id, request));
    }

    @PostMapping("/creature/{id}/hit")
    public Boolean hitByCreature(@PathVariable Long id, @RequestBody HitFullRequest request) {
        return gameService.hitByCreature(id, request.getTargetId(), request.getTargetType(), request.getD20hit(), request.getHitRequest());
    }

    @PostMapping("/creature/{id}/give_item")
    public CreatureResponse giveItemToCreature(@PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.giveItemCreature(id, itemId));
    }

    @PostMapping("/creature/{id}/take_item")
    public CreatureResponse takeItemFromCreature(@PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.takeItemCreature(id, itemId));
    }

    @PostMapping("/creature/{id}/equip_item")
    public CreatureResponse equipItemToCreature(@PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.equipItemCreature(id, itemId));
    }

    @PostMapping("/creature/{id}/unequip_item")
    public CreatureResponse unequipItemToCreature(@PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.unequipItemCreature(id, itemId));
    }

    @PostMapping("/creature/{id}/relocate")
    public CreatureResponse relocateCreature(@PathVariable Long id, @RequestBody Long locationId) {
        return new CreatureResponse(gameService.relocateCreature(id, locationId));
    }

    @PostMapping("/destructible_object/{id}/move")
    public DestructibleObjectResponse moveDestructibleObject(@PathVariable Long id, @RequestBody List<Position> path) {
        return new DestructibleObjectResponse(gameService.moveDestructibleObject(id, path));
    }

    @PostMapping("/destructible_object/{id}/damage")
    public DestructibleObjectResponse damageDestructibleObject(@PathVariable Long id, @RequestBody DamageRequest request) {
        return new DestructibleObjectResponse(gameService.damageDestructibleObject(id, request));
    }

    @PostMapping("/destructible_object/{id}/relocate")
    public DestructibleObjectResponse relocateDestructibleObject(@PathVariable Long id, @RequestBody Long locationId) {
        return new DestructibleObjectResponse(gameService.relocateDestructibleObject(id, locationId));
    }

    @PostMapping("/item/{id}/move")
    public ItemResponse moveItem(@PathVariable Long id, @RequestBody List<Position> path) {
        return new ItemResponse(gameService.moveItem(id, path));
    }

    @PostMapping("/item/{id}/relocate")
    public ItemResponse relocateItem( @PathVariable Long id, @RequestBody Long locationId) {
        return new ItemResponse(gameService.relocateItem(id, locationId));
    }

}
