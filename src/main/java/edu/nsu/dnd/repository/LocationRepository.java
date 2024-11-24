package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
