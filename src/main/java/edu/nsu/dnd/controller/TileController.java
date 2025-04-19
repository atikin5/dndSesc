package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.TileRequest;
import edu.nsu.dnd.model.dto.responses.TileResponse;
import edu.nsu.dnd.service.TileService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/tile")
public class TileController {

    private final TileService tileService;

    @GetMapping("/{id}")
    public TileResponse get(@PathVariable Long id) {
        return new TileResponse(tileService.get(id));
    }

    @PostMapping()
    public TileResponse create(@RequestBody TileRequest request) {
        return new TileResponse(tileService.create(request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tileService.delete(id);
    }

    @PutMapping("/{id}")
    public TileResponse update(@PathVariable Long id, @RequestBody TileRequest request) {
        return new TileResponse(tileService.update(id, request));
    }

    @GetMapping("/location/{id}")
    public List<TileResponse> tilesByLocationId(@PathVariable Long id) {
        return tileService.tilesByLocationId(id).stream().map(TileResponse::new).toList();
    }

}
