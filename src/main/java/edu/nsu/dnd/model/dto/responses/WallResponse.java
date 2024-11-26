package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.enums.Orientation;
import edu.nsu.dnd.model.persistent.Wall;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallResponse {
    private Long id;
    private String type;
    private Position position;
    private Boolean visible;
    private Boolean walkable;
    private Orientation orientation;
    private boolean operational;
    private int currentHp;
    private int maxHp;
    private int armorClass;
    private int durability;
    private Map<DamageType, DamageMultiplier> damageMultipliers = new HashMap<>();

    public WallResponse (Wall wall) {
        id = wall.getId();
        type = wall.getType();
        position = wall.getPosition();
        visible = wall.getVisible();
        walkable = wall.getWalkable();
        orientation = wall.getOrientation();
        operational = wall.isOperational();
        currentHp = wall.getCurrentHp();
        maxHp = wall.getMaxHp();
        armorClass = wall.getArmorClass();
        durability = wall.getDurability();
        damageMultipliers.putAll(wall.getDamageMultipliers());
    }
}
