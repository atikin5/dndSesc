package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.ItemRequest;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.ItemRepository;
import edu.nsu.dnd.service.CampaignService;
import edu.nsu.dnd.service.ItemService;
import edu.nsu.dnd.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;
    CampaignService campaignService;
    LocationService locationService;

    @Override
    public Item get(Long id) {
        return null;
    }

    @Override
    public List<Item> getByCampaignId(Long campaignId) {
        return List.of();
    }

    @Override
    public List<Item> getByLocationId(Long locationId) {
        return List.of();
    }

    @Override
    public List<Item> getByOwnerId(Long ownerId) {
        return List.of();
    }

    public void fillFields(ItemRequest itemRequest, Item item) {
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
}
