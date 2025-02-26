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

    @PutMapping("/{id}")
    public DndCharacterResponse update(@PathVariable Long id, @RequestBody DndCharacterRequest request) {
        return new DndCharacterResponse(dndCharacterService.update(id,request));
    }
}
