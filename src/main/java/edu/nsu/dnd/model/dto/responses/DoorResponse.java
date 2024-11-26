package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.persistent.Door;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoorResponse {
    private Long id;
    private String type;
    private Position position;
    private boolean operational = true;
    private int currentHp;
    private int maxHp;
    private int armorClass;
    private int durability;
    private Boolean visible;
    private Boolean walkable;

    public DoorResponse(Door door) {
        id = door.getId();
        type = door.getType();
        position = door.getPosition();
        operational = door.isOperational();
        currentHp = door.getCurrentHp();
        maxHp = door.getMaxHp();
        armorClass = door.getArmorClass();
        durability = door.getDurability();
        visible = door.getVisible();
        walkable = door.getWalkable();
    }
}
