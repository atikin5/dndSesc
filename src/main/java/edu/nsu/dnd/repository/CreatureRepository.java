package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long> {

    List<Creature> findByCampaignId(Long campaignId);

    List<Creature> findByLocationId(Long locationId);

}
