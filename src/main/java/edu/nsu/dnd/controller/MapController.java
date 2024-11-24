package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.persistent.Location;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/map")
public class MapController {

    private static final Map<Integer, Location> GAME_MAPS = new HashMap<>();

    @GetMapping("/{id}")
    public Location getMap(@PathVariable int id) {
        return GAME_MAPS.get(id);
//        Location gameMap = new Location();
//        Tile[][] tiles = {
//                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)},
//                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)},
//                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)}
//        };
//        gameMap.setTiles(tiles);
//        return gameMap;
    }

    @PostMapping("/{id}")
    public void setMap(@PathVariable int id, @RequestBody Location location) {
        GAME_MAPS.put(id, location);
    }
}
