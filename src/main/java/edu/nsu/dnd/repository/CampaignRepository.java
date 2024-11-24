package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.model.persistent.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}
