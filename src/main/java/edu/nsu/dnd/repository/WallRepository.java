package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Wall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallRepository extends JpaRepository<Wall, Long> {

}
