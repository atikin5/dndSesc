package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.responses.LocationResponse;
import edu.nsu.dnd.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/{id}")
    public LocationResponse getLocationById(@PathVariable Long id) {
        return new LocationResponse(locationService.get(id));
    }

    

}
