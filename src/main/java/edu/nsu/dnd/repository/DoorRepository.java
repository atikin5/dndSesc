package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {

}
