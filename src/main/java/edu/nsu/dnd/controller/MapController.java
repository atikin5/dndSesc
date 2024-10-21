package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.GameMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/map")
public class MapController {

    private static final Map<Integer, GameMap> GAME_MAPS = new HashMap<>();

    @GetMapping("/{id}")
    public GameMap getMap(@PathVariable int id) {
        return GAME_MAPS.get(id);
//        GameMap gameMap = new GameMap();
//        Tile[][] tiles = {
//                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)},
//                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)},
//                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)}
//        };
//        gameMap.setTiles(tiles);
//        return gameMap;
    }

    @PostMapping("/{id}")
    public void setMap(@PathVariable int id, @RequestBody GameMap gameMap) {
        GAME_MAPS.put(id, gameMap);
    }
}
