package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.embeddable.ItemPosition;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private Long id;
    private String type;
    private Position position;
    private String name;
    private String description;
    private ItemPosition itemPosition;
    private List<DamageType> commonDamage;
    private List<DamageType> optionalDamage;
    private List<DamageType> missDamage;

    public ItemResponse(Item item) {
        id = item.getId();
        type = item.getType();
        position = item.getPosition();
        name = item.getName();
        description = item.getDescription();
        itemPosition = item.getItemPosition();
        commonDamage = item.getCommonDamage();
        optionalDamage = item.getOptionalDamage();
        missDamage = item.getMissDamage();
    }
}
