package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.GameMap;
import edu.nsu.dnd.model.Tile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/map")
public class MapController {
    @GetMapping("/{id}")
    public GameMap getMap(@PathVariable int id) {
        GameMap gameMap = new GameMap();
        Tile[][] tiles = {
                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)},
                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)},
                new Tile[]{new Tile(true, true), new Tile(true, true), new Tile(true, true)}
        };
        gameMap.setTiles(tiles);
        return gameMap;
    }
}
