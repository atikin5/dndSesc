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

    @PostMapping("/campaign/{campaignId}/character/{id}/move")
    public DndCharacterResponse moveDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody List<Position> path) {
        return new DndCharacterResponse(gameService.moveCharacter(campaignId, id, path));
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/skill_check")
    public SkillCheckResponse skillCheckDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody SkillCheckRequest request) {
        return gameService.skillCheckCharacter(campaignId, id, request);
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/damage")
    public DndCharacterResponse damageDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody DamageRequest request) {
        return new DndCharacterResponse(gameService.damageCharacter(campaignId, id, request));
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/hit")
    public Boolean hitByDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody HitFullRequest request) {
        return gameService.hitByCharacter(campaignId, id, request.getTargetId(), request.getTargetType(), request.getD20hit(), request.getHitRequest());
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/give_item")
    public DndCharacterResponse giveItemToDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.giveItemCharacter(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/take_item")
    public DndCharacterResponse takeItemFromDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.takeItemCharacter(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/equip_item")
    public DndCharacterResponse equipItemToDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.equipItemCharacter(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/unequip_item")
    public DndCharacterResponse unequipItemToDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new DndCharacterResponse(gameService.unequipItemCharacter(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/character/{id}/relocate")
    public DndCharacterResponse relocateDndCharacter(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long locationId) {
        return new DndCharacterResponse(gameService.relocateCharacter(campaignId, id, locationId));
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/move")
    public CreatureResponse moveCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody List<Position> path) {
        return new CreatureResponse(gameService.moveCreature(campaignId, id, path));
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/skill_check")
    public SkillCheckResponse skillCheckCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody SkillCheckRequest request) {
        return gameService.skillCheckCreature(campaignId, id, request);
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/damage")
    public CreatureResponse damageCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody DamageRequest request) {
        return new CreatureResponse(gameService.damageCreature(campaignId, id, request));
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/hit")
    public Boolean hitByCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody HitFullRequest request) {
        return gameService.hitByCreature(campaignId, id, request.getTargetId(), request.getTargetType(), request.getD20hit(), request.getHitRequest());
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/give_item")
    public CreatureResponse giveItemToCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.giveItemCreature(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/take_item")
    public CreatureResponse takeItemFromCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.takeItemCreature(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/equip_item")
    public CreatureResponse equipItemToCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.equipItemCreature(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/unequip_item")
    public CreatureResponse unequipItemToCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long itemId) {
        return new CreatureResponse(gameService.unequipItemCreature(campaignId, id, itemId));
    }

    @PostMapping("/campaign/{campaignId}/creature/{id}/relocate")
    public CreatureResponse relocateCreature(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long locationId) {
        return new CreatureResponse(gameService.relocateCreature(campaignId, id, locationId));
    }

    @PostMapping("/campaign/{campaignId}/destructible_object/{id}/move")
    public DestructibleObjectResponse moveDestructibleObject(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody List<Position> path) {
        return new DestructibleObjectResponse(gameService.moveDestructibleObject(campaignId, id, path));
    }

    @PostMapping("/campaign/{campaignId}/destructible_object/{id}/damage")
    public DestructibleObjectResponse damageDestructibleObject(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody DamageRequest request) {
        return new DestructibleObjectResponse(gameService.damageDestructibleObject(campaignId, id, request));
    }

    @PostMapping("/campaign/{campaignId}/destructible_object/{id}/relocate")
    public DestructibleObjectResponse relocateDestructibleObject(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long locationId) {
        return new DestructibleObjectResponse(gameService.relocateDestructibleObject(campaignId, id, locationId));
    }

    @PostMapping("/campaign/{campaignId}/item/{id}/move")
    public ItemResponse moveItem(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody List<Position> path) {
        return new ItemResponse(gameService.moveItem(campaignId, id, path));
    }

    @PostMapping("/campaign/{campaignId}/item/{id}/relocate")
    public ItemResponse relocateItem(@PathVariable Long campaignId, @PathVariable Long id, @RequestBody Long locationId) {
        return new ItemResponse(gameService.relocateItem(campaignId, id, locationId));
    }

}
