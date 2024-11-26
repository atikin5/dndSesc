package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findByCampaignId(Long campaignId, Pageable pageable);
}
