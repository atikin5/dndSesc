package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.DndCharacterRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.DndCharacterResponse;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.service.DndCharacterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/character")
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

    @GetMapping("/page-campaign/{campaignId}")
    public Page<DndCharacterResponse> getPageByCampaignId(@PathVariable Long campaignId, @RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dndCharacterService.getPageByCampaignId(campaignId, pageable).map(DndCharacterResponse::new);
    }

    @GetMapping("/page-location/{locationId}")
    public Page<DndCharacterResponse> getPageByLocationId(@PathVariable Long locationId, @RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dndCharacterService.getPageByLocationId(locationId, pageable).map(DndCharacterResponse::new);
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
    public DndCharacterResponse damage(@PathVariable Long id, @RequestBody DamageRequest damageRequest) {
        return new DndCharacterResponse(dndCharacterService.damage(id, damageRequest));
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
        return dndCharacterService.skillCheck(id, request);
    }
}
