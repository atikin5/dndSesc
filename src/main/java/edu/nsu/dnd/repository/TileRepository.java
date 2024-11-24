package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Location;
import edu.nsu.dnd.model.persistent.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepository extends JpaRepository<Tile, Long> {

}
