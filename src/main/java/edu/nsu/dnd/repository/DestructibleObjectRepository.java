package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.DestructibleObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestructibleObjectRepository extends JpaRepository<DestructibleObject, Long> {

}
