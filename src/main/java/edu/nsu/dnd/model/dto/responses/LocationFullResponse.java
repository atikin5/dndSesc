package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.persistent.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationFullResponse {
    private Long id;
    private String name;
    private List<DndCharacterResponse> dndCharacters = new ArrayList<>();
    private List<CreatureResponse> creatures = new ArrayList<>();
    private List<DestructibleObjectResponse> destructibleObjects = new ArrayList<>();
    private List<TileResponse> tiles = new ArrayList<>();
    private List<WallResponse> walls = new ArrayList<>();
    private List<DoorResponse> doors = new ArrayList<>();

    public LocationFullResponse(Location location) {
        id = location.getId();
        name = location.getName();
        dndCharacters = location.getDndCharacters().stream().map(DndCharacterResponse::new).toList();
        creatures = location.getCreatures().stream().map(CreatureResponse::new).toList();
        destructibleObjects = location.getDestructibleObjects().stream().map(DestructibleObjectResponse::new).toList();
        tiles = location.getTiles().stream().map(TileResponse::new).toList();
        walls = location.getWalls().stream().map(WallResponse::new).toList();
        doors = location.getDoors().stream().map(DoorResponse::new).toList();
    }
}
