package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.Condition;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DestructibleObject;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestructibleObjectResponse {
    private Long id;
    private String type;
    private Position position;
    private boolean operational;
    private int currentHp;
    private int maxHp;
    private int temporaryHp;
    private int armorClass;
    private Map<DamageType, DamageMultiplier> damageMultipliers;
    private Size size;
    private List<Condition> conditions = new ArrayList<>();

    public DestructibleObjectResponse(DestructibleObject destructibleObject) {
        id = destructibleObject.getId();
        type = destructibleObject.getType();
        position = destructibleObject.getPosition();
        operational = destructibleObject.isOperational();
        currentHp = destructibleObject.getCurrentHp();
        maxHp = destructibleObject.getMaxHp();
        armorClass = destructibleObject.getArmorClass();
        size = destructibleObject.getSize();
    }
}
