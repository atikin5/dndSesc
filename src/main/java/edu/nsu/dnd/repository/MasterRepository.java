package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {

}
