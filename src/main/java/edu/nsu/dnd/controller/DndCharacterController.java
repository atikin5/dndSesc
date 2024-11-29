package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.DndCharacterRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.DndCharacterResponse;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.service.DndCharacterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/dnd-character")
public class DndCharacterController {

    private final DndCharacterService dndCharacterService;

    @GetMapping("/campaign/{campaignId}")
    public List<DndCharacterResponse> getDndCharacterByCampaignId(@PathVariable Long campaignId) {
        return dndCharacterService.getByCampaignId(campaignId).stream().map(DndCharacterResponse::new).toList();
    }

    @GetMapping("/location/{locationId}")
    public List<DndCharacterResponse> getDndCharacterByLocationId(@PathVariable Long locationId) {
        return dndCharacterService.getByLocationId(locationId).stream().map(DndCharacterResponse::new).toList();
    }

    @GetMapping("/{id}")
    public DndCharacterResponse getDndCharacterById(@PathVariable Long id) {
        return new DndCharacterResponse(dndCharacterService.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteDndCharacterById(@PathVariable Long id) {
        dndCharacterService.delete(id);
    }

    @PostMapping()
    public DndCharacterResponse create(@RequestBody DndCharacterRequest request) {
        return new DndCharacterResponse(dndCharacterService.create(request));
    }

    @PostMapping("/{id}/replace")
    public DndCharacterResponse replace(@PathVariable Long id, @RequestBody Position position) {
        return new DndCharacterResponse(dndCharacterService.move(id, position));
    }

    @PostMapping("/{id}/damage")
    public DndCharacterResponse damage(@PathVariable Long id, @RequestBody Damage damage) {
        return new DndCharacterResponse(dndCharacterService.damage(id, damage));
    }

    @PostMapping("/{id}/heal")
    public DndCharacterResponse heal(@PathVariable Long id, @RequestBody int healAmount) {
        return new DndCharacterResponse(dndCharacterService.heal(id, healAmount));
    }

    @PostMapping("/{id}/resize")
    public DndCharacterResponse resize(@PathVariable Long id, @RequestBody Size size) {
        return new DndCharacterResponse(dndCharacterService.resize(id, size));
    }

    @PostMapping("/{id}/relocate/{locationId}")
    public DndCharacterResponse relocate(@PathVariable Long id, @PathVariable Long locationId ) {
        return new DndCharacterResponse(dndCharacterService.relocate(id, locationId));
    }

    @PostMapping("/{id}/skill-check")
    public SkillCheckResponse skillCheck(@PathVariable Long id, @RequestBody SkillCheckRequest request) {
        return new SkillCheckResponse(dndCharacterService.skillCheck(id, request))
    }
}
