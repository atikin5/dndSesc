package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.LocationRequest;
import edu.nsu.dnd.model.persistent.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {

    Location get(Long id);

    Location create(LocationRequest locationRequest);

    Page<Location> page(Long campaignId , Pageable pageable);

    Location update(Long id, LocationRequest locationRequest);

    void delete(Long id);

}
