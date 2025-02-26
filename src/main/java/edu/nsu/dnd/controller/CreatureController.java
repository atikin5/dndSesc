package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.CreatureRequest;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.CreatureResponse;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.service.CreatureService;
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
@RequestMapping("/creature")
public class CreatureController {

    private final CreatureService creatureService;

    @GetMapping("/campaign/{campaignId}")
    public List<CreatureResponse> getCreaturesByCampaignId(@PathVariable Long campaignId) {
        return creatureService.getPageByCampaignId(campaignId).stream().map(CreatureResponse::new).toList();
    }

    @GetMapping("/location/{locationId}")
    public List<CreatureResponse> getCreaturesByLocationId(@PathVariable Long locationId) {
        return creatureService.getByLocationId(locationId).stream().map(CreatureResponse::new).toList();
    }

    @GetMapping("/page-campaign/{campaignId}")
    public Page<CreatureResponse> getCreaturesByCampaignId(@PathVariable Long campaignId, @RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return creatureService.getPageByCampaignId(campaignId, pageable).map(CreatureResponse::new);
    }

    @GetMapping("page-location/{locationId}")
    public Page<CreatureResponse> getPageByLocationId(@PathVariable Long locationId, @RequestParam(required = false) int page, @RequestParam(required = false) int size ) {
        Pageable pageable = PageRequest.of(page, size);
        return creatureService.getPageByLocationId(locationId, pageable).map(CreatureResponse::new);
    }

    @GetMapping("/{id}")
    public CreatureResponse getCreatureById(@PathVariable Long id) {
        return new CreatureResponse(creatureService.get(id));
    }

    @PutMapping("/{id}/update")
    public CreatureResponse updateCreature(@PathVariable Long id, @RequestBody CreatureRequest request) {
        return new CreatureResponse(creatureService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteCreatureById(@PathVariable Long id) {
        creatureService.delete(id);
    }

    @PostMapping()
    public CreatureResponse create(@RequestBody CreatureRequest request) {
        return new CreatureResponse(creatureService.create(request));
    }

}