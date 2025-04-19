package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.TileRequest;
import edu.nsu.dnd.model.persistent.Tile;

import java.util.List;

public interface TileService {

    Tile get(Long id);

    Tile create(TileRequest request);

    Tile update(Long id, TileRequest request);

    void delete(Long id);

    List<Tile> tilesByLocationId(Long locationId);
}
