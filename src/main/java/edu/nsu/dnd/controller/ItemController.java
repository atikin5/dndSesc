package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.ItemRequest;
import edu.nsu.dnd.model.dto.responses.ItemResponse;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.service.ItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ItemResponse getItemById(@PathVariable Long id) {
        return new ItemResponse(itemService.get(id));
    }

    @GetMapping("/campaign/{id}")
    public List<ItemResponse> getItemByCampaignId(@PathVariable Long id) {
        return itemService.getByCampaignId(id).stream().map(ItemResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/campaign/unused/{id}")
    public List<ItemResponse> getUnusedItemsByCampaignId(@PathVariable Long id) {
        if (itemService.getUnusedItemsByCampaignId(id) != null) {
            return itemService.getUnusedItemsByCampaignId(id).stream().map(ItemResponse::new).collect(Collectors.toList());

        } else {
            return new ArrayList<ItemResponse>();
        }
    }

    @GetMapping("/location/{id}")
    public List<ItemResponse> getItemByLocationId(@PathVariable Long id) {
        return itemService.getByLocationId(id).stream().map(ItemResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/creature_backpack/{id}")
    public List<ItemResponse> getItemByCreatureBackpackId(@PathVariable Long id) {
        return itemService.getByCreatureBackpackId(id).stream().map(ItemResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/creature_equipment/{id}")
    public List<ItemResponse> getItemByCreatureEquipmentId(@PathVariable Long id) {
        return itemService.getByCreatureEquipmentId(id).stream().map(ItemResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/character_backpack/{id}")
    public List<ItemResponse> getItemsByCharacterBackpackId(@PathVariable Long id) {
        return itemService.getByDndCharacterBackpackId(id).stream().map(ItemResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/character_equipment/{id}")
    public List<ItemResponse> getItemsByCharacterEquipmentId(@PathVariable Long id) {
        return itemService.getByDndCharacterEquipmentId(id).stream().map(ItemResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ItemResponse create(@RequestBody ItemRequest itemRequest) {
        return new ItemResponse(itemService.create(itemRequest));
    }

    @PostMapping("/{id}/update")
    public ItemResponse update(@PathVariable Long id, @RequestBody ItemRequest itemRequest) {
        return new ItemResponse(itemService.update(id, itemRequest));
    }

    @PostMapping("/{id}/move")
    public ItemResponse move(@PathVariable Long id, @RequestBody Position position) {
        return new ItemResponse(itemService.move(id, position));
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

    @PostMapping("/{id}/relocate")
    public ItemResponse relocate(@PathVariable Long id, @RequestBody Long locationId) {
        return new ItemResponse(itemService.relocate(id, locationId));
    }

}
