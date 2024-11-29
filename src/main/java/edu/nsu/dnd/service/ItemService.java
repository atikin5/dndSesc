package edu.nsu.dnd.service;

import edu.nsu.dnd.model.persistent.Item;

import java.util.List;

public interface ItemService {

    Item get(Long id);

    List<Item> getByCampaignId(Long campaignId);

    List<Item> getByLocationId(Long locationId);

    List<Item> getByOwnerId(Long ownerId);

}
