package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.DndCharacter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DndCharacterRepository extends JpaRepository<DndCharacter, Long> {

    List<DndCharacter> findPageByCampaignId(Long campaignId);

    List<DndCharacter> findByLocationId(Long locationId);

    Page<DndCharacter> findPageByCampaignId(Long campaignId, Pageable pageable);

    Page<DndCharacter> findPageByLocationId(Long locationId, Pageable pageable);

}
