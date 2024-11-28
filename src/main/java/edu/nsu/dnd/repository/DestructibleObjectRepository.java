package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.DestructibleObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestructibleObjectRepository extends JpaRepository<DestructibleObject, Long> {

    List<DestructibleObject> findByCampaignId(Long campaignId);

    List<DestructibleObject> findByLocationId(Long locationId);

}
