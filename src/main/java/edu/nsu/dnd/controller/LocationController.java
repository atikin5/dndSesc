package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.LocationRequest;
import edu.nsu.dnd.model.dto.responses.LocationFullResponse;
import edu.nsu.dnd.model.dto.responses.LocationResponse;
import edu.nsu.dnd.model.persistent.Location;
import edu.nsu.dnd.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/{id}")
    public LocationResponse get(@PathVariable Long id) {
        return new LocationResponse(locationService.get(id));
    }

    @GetMapping("/{id}/full")
    public LocationFullResponse getFull(@PathVariable Long id) {return new LocationFullResponse(locationService.get(id));}

    @PostMapping
    public LocationResponse create(@RequestBody LocationRequest locationRequest) {
        return new LocationResponse(locationService.create(locationRequest));
    }

    @GetMapping("/page")
    public Page<LocationResponse> page(@RequestParam(required = false) Long campaignId, @RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        return locationService.page(campaignId, pageable).map(LocationResponse::new);
    }

}
