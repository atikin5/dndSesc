package edu.nsu.dnd.model.dto;

import edu.nsu.dnd.model.persistent.Tile;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TileResponse {
    private Long id;
    private String type;
    private Position position;
    private Boolean visibleByCharacter;

    public TileResponse(Tile tile) {
        id = tile.getId();
        type = tile.getType();
        position = tile.getPosition();
        visibleByCharacter = tile.getVisibleByCharacter();
    }
}
