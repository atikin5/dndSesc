package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.TileRequest;
import edu.nsu.dnd.model.persistent.Tile;
import edu.nsu.dnd.repository.TileRepository;
import edu.nsu.dnd.service.CampaignService;
import edu.nsu.dnd.service.LocationService;
import edu.nsu.dnd.service.TileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TileServiceImpl implements TileService {

    private final TileRepository tileRepository;
    private final CampaignService campaignService;
    private final LocationService locationService;

    @Override
    public Tile get(Long id) {
        return tileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tile with id " + id + " not found"));
    }

    private void fillFields(TileRequest request, Tile tile) {
        tile.setLocation(locationService.get(request.getLocationId()));
        tile.setVisible(request.getVisible());
        tile.setWalkable(request.getWalkable());
        tile.setVisibleByCharacter(request.getVisibleByCharacter());
        tile.setPosition(request.getPosition());
        tile.setType(request.getType());
    }

    @Override
    public Tile create(TileRequest request) {
        Tile tile = new Tile();
        tile.setCampaign(campaignService.get(request.getCampaignId()));
        fillFields(request, tile);
        return tileRepository.save(tile);
    }

    @Override
    public Tile update(Long id, TileRequest request) {
        Tile tile = get(id);
        fillFields(request, tile);
        return tileRepository.save(tile);
    }

    @Override
    public void delete(Long id) {
        Tile tile = get(id);
        tileRepository.delete(tile);
    }

    @Override
    public List<Tile> tilesByLocationId(Long locationId) {
        return tileRepository.findTileByLocationId(locationId);
    }
}
