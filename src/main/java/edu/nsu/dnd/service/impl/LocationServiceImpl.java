package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.LocationRequest;
import edu.nsu.dnd.model.persistent.Location;
import edu.nsu.dnd.repository.LocationRepository;
import edu.nsu.dnd.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Location> getByCampaignId(Long campaignId) {
        return locationRepository.findByCampaignId(campaignId);
    }

    @Override
    public Location create(LocationRequest request) {
        Location location = new Location();
        location.setCampaign(campaignService.get(request.getCampaignId()));
        location.setName(request.getName());
        return locationRepository.save(location);
    }

    @Override
    public Page<Location> page(Long campaignId, Pageable pageable) {
        return locationRepository.findPageByCampaignId(campaignId, pageable);
    }

    @Override
    public Location update(Long locationId, LocationRequest locationRequest) {
        Location location = get(locationId);
        location.setName(locationRequest.getName());
        return locationRepository.save(location);
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }


}
