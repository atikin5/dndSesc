package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.LocationRequest;
import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.model.persistent.Location;
import edu.nsu.dnd.repository.LocationRepository;
import edu.nsu.dnd.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final CampaignServiceImpl campaignService;

    @Override
    public Location get(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location with id " + id + " not found"));
    }

    @Override
    public Location create(Long campaignId, LocationRequest locationRequest) {
        Campaign campaign = campaignService.get(campaignId);
        Location location = new Location();
        location.setCampaign(campaign);
        return locationRepository.save(location);
    }

    @Override
    public Page<Location> page(Long campaignId, Pageable pageable) {
        Campaign campaign = campaignService.get(campaignId);
        return (Page<Location>) locationRepository.findByCampaignId(campaignId, pageable);
    }

    @Override
    public Location update(Long locationId, LocationRequest locationRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
