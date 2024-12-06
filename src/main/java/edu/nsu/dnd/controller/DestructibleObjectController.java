package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.DestructibleObjectRequest;
import edu.nsu.dnd.model.dto.responses.DestructibleObjectResponse;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.service.DestructibleObjectService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/destructible-object")
public class DestructibleObjectController {

    private final DestructibleObjectService destructibleObjectService;

    @GetMapping("/campaign/{campaignId}")
    public List<DestructibleObjectResponse> getAllDestructibleObjectByCampaignId(@PathVariable Long campaignId) {
        return destructibleObjectService.getByCampaignId(campaignId).stream()
                .map(DestructibleObjectResponse::new).toList();
    }

    @GetMapping("/location/{locationId}")
    public List<DestructibleObjectResponse> getAllDestructibleObjectByLocationId(@PathVariable Long locationId) {
        return destructibleObjectService.getByLocationId(locationId).stream()
                .map(DestructibleObjectResponse::new).toList();
    }

    @GetMapping("/{id}")
    public DestructibleObjectResponse getDestructibleObjectById(@PathVariable Long id) {
        return new DestructibleObjectResponse(destructibleObjectService.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteDestructibleObjectById(@PathVariable Long id) {
        destructibleObjectService.delete(id);
    }

    @PostMapping()
    public DestructibleObjectResponse create(@RequestBody DestructibleObjectRequest request) {
        return new DestructibleObjectResponse(destructibleObjectService.create(request));
    }

    @PutMapping("/{id}")
    public DestructibleObjectResponse update(@PathVariable Long id, @RequestBody DestructibleObjectRequest request) {
        return new DestructibleObjectResponse(destructibleObjectService.update(id, request));
    }

    @PostMapping("/{id}/move")
    public DestructibleObjectResponse move(@PathVariable Long id, @RequestBody Position position) {
        return new DestructibleObjectResponse(destructibleObjectService.move(id, position));
    }

    @PostMapping("/{id}/damage")
    public DestructibleObjectResponse damage(@PathVariable Long id, @RequestBody DamageRequest damageRequest) {
        return new DestructibleObjectResponse(destructibleObjectService.damage(id, damageRequest));
    }

    @PostMapping("/{id}/heal")
    public DestructibleObjectResponse heal(@PathVariable Long id, @RequestBody int healAmount) {
        return new DestructibleObjectResponse(destructibleObjectService.heal(id, healAmount));
    }

    @PostMapping("/{id}/resize")
    public DestructibleObjectResponse resize(@PathVariable Long id, @RequestBody Size size) {
        return new DestructibleObjectResponse(destructibleObjectService.resize(id, size));
    }
}
