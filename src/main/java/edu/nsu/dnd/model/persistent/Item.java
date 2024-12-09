package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.enums.Ability;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.persistent.base.MovableObject;
import edu.nsu.dnd.model.persistent.embeddable.ItemPosition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Носимый предмет.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item extends MovableObject {

    private String name;
    private String description;
    private ItemPosition itemPosition;
    private boolean melee;
    private int bonus;
    private Ability attackAbility;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DamageType> commonDamage = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DamageType> optionalDamage = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DamageType> missDamage = new ArrayList<>();

    public Boolean equip(List<Item> equippedItems, ItemPosition maxItemPosition) {
        int sumHandPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getHandPosition()).sum() + itemPosition.getHandPosition();
        int sumFingerPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getFingerPosition()).sum() + itemPosition.getFingerPosition();
        int sumHeadPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getHeadPosition()).sum() + itemPosition.getHeadPosition();
        int sumBodyPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getBodyPosition()).sum() + itemPosition.getBodyPosition();
        int sumFootPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getFootPosition()).sum() + itemPosition.getFootPosition();
        int sumCloakPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getCloakPosition()).sum() + itemPosition.getCloakPosition();
        int sumLegsPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getLegsPosition()).sum() + itemPosition.getLegsPosition();
        int sumNeckPosition = equippedItems.stream().mapToInt(equippedItem -> equippedItem.getItemPosition().getNeckPosition()).sum() + itemPosition.getNeckPosition();
        return (sumHandPosition <= maxItemPosition.getHandPosition()
                && sumFingerPosition <= maxItemPosition.getFingerPosition()
                && sumHeadPosition <= maxItemPosition.getHeadPosition()
                && sumBodyPosition <= maxItemPosition.getBodyPosition()
                && sumFootPosition <= maxItemPosition.getFootPosition()
                && sumCloakPosition <= maxItemPosition.getCloakPosition()
                && sumLegsPosition <= maxItemPosition.getLegsPosition()
                && sumNeckPosition <= maxItemPosition.getNeckPosition());
    }
}
