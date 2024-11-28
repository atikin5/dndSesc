package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.DndCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DndCharacterRepository extends JpaRepository<DndCharacter, Long> {

    List<DndCharacter> findByCampaignId(Long campaignId);

    List<DndCharacter> findByLocationId(Long locationId);

}
