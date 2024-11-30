package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.repository.ItemRepository;
import edu.nsu.dnd.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

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
}
