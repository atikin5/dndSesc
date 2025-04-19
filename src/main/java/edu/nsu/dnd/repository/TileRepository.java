package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TileRepository extends JpaRepository<Tile, Long> {

    List<Tile> findTileByLocationId(Long locationId);

}
