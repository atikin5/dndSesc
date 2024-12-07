package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCampaignId(Long campaignId);

    List<Item> findByLocationId(Long locationId);

}
