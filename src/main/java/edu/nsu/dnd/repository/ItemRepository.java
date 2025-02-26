package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCampaignId(Long campaignId);

    List<Item> findByLocationId(Long locationId);

    @Query("FROM Item i WHERE i.location IS NULL AND"  +
            " NOT EXISTS (SELECT 1 FROM DndCharacter dc WHERE i MEMBER dc.backpackItems OR i MEMBER dc.equippedItems)" +
            " AND NOT EXISTS (SELECT 1 FROM Creature c WHERE i MEMBER c.backpackItems OR i MEMBER c.equippedItems)")
    List<Item> findUnusedItemsByCampaignId(Long campaignId);

}
