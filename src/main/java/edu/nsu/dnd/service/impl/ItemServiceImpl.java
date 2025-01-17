package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.ItemRequest;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.CreatureRepository;
import edu.nsu.dnd.repository.DndCharacterRepository;
import edu.nsu.dnd.repository.ItemRepository;
import edu.nsu.dnd.service.CampaignService;
import edu.nsu.dnd.service.ItemService;
import edu.nsu.dnd.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CampaignService campaignService;
    private final LocationService locationService;
    private final DndCharacterRepository dndCharacterRepository;
    private final CreatureRepository creatureRepository;

    @Override
    public Item get(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found"));
    }

    @Override
    public List<Item> getByCampaignId(Long campaignId) {
        return List.of();
    }

    @Override
    public List<Item> getByLocationId(Long locationId) {
        return List.of();
    }

    private void fillFields(ItemRequest itemRequest, Item item) {
        item.setCampaign(campaignService.get(itemRequest.getCampaignId()));
    }

    @Override
    public Item create(ItemRequest itemRequest) {
        Item item = new Item();
        item.setType(itemRequest.getType());
        fillFields(itemRequest, item);
        return itemRepository.save(item);
    }

    @Override
    public Item update(Long id, ItemRequest itemRequest) {
        Item item = get(id);
        fillFields(itemRequest, item);
        return itemRepository.save(item);
    }

    @Override
    public Item move(Long id, Position position) {
        Item item = get(id);
        item.setPosition(position);
        return itemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(get(id));
    }

    @Override
    public Item relocate(Long id, Long locationId) {
        Item item = get(id);
        item.setLocation(locationService.get(locationId));
        Position position = new Position();
        item.setPosition(position);
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getByDndCharacterBackpackId(Long characterId) {
        DndCharacter dndCharacter =  dndCharacterRepository.findById(characterId)
                .orElseThrow(() -> new EntityNotFoundException("Character with id " + characterId + " not found"));
        return dndCharacter.getBackpackItems();
    }

    @Override
    public List<Item> getByDndCharacterEquipmentId(Long characterId) {
        DndCharacter dndCharacter =  dndCharacterRepository.findById(characterId)
                .orElseThrow(() -> new EntityNotFoundException("Character with id " + characterId + " not found"));
        return dndCharacter.getEquippedItems();
    }

    @Override
    public List<Item> getByCreatureBackpackId(Long creatureId) {
        Creature creature =  creatureRepository.findById(creatureId)
                .orElseThrow(() -> new EntityNotFoundException("Creature with id " + creatureId + " not found"));
        return creature.getBackpackItems();
    }

    @Override
    public List<Item> getByCreatureEquipmentId(Long creatureId) {
        Creature creature =  creatureRepository.findById(creatureId)
                .orElseThrow(() -> new EntityNotFoundException("Creature with id " + creatureId + " not found"));
        return creature.getEquippedItems();
    }
}
