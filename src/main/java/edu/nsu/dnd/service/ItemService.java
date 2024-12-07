package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.ItemRequest;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.embeddable.Position;

import java.util.List;

public interface ItemService {

    Item get(Long id);

    Item create(ItemRequest request);

    Item update(Long id, ItemRequest request);

    List<Item> getByCampaignId(Long campaignId);

    List<Item> getByLocationId(Long locationId);

    List<Item> getByDndCharacterBackpackId(Long characterId);

    List<Item> getByDndCharacterEquipmentId(Long characterId);

    List<Item> getByCreatureBackpackId(Long creatureId);

    List<Item> getByCreatureEquipmentId(Long creatureId);

    void delete(Long id);

    Item move(Long id, Position position);

    Item relocate(Long id, Long locationId);

}
