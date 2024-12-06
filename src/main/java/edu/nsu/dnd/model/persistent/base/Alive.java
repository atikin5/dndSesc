package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.enums.Race;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import edu.nsu.dnd.model.persistent.embeddable.ItemPosition;
import edu.nsu.dnd.model.persistent.embeddable.Skills;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
public abstract class Alive extends Destructible {

    /** TODO бонус мастерства, просчет атаки
    */
    private ItemPosition itemPosition = new ItemPosition(2, 10, 1, 1, 1, 1, 1, 1);

    @ManyToMany
    private List<Item> backpackItems = new ArrayList<>();

    @ManyToMany
    private List<Item> equippedItems = new ArrayList<>();

    @Embedded
    private Abilities abilities;

    @Enumerated(EnumType.STRING)
    private Race race;

    private int maxMovement = 30;
    private int movement;

    private int maxAction = 1;
    private int action;

    private int maxBonusAction = 1;
    private int bonusAction;

    private int maxReaction = 1;
    private int reaction;


    private int proficiencyBonus;
    private Skills skills = new Skills();

    public void equip(Item item) {
        if (!equippedItems.contains(item) && backpackItems.contains(item)) {
            if (item.getItemPosition().getBodyPosition() <= itemPosition.getBodyPosition()
                    && item.getItemPosition().getFingerPosition() <= itemPosition.getFingerPosition()
                    && item.getItemPosition().getHeadPosition() <= itemPosition.getHeadPosition()
                    && item.getItemPosition().getHandPosition() <= itemPosition.getHandPosition()
                    && item.getItemPosition().getLegsPosition() <= itemPosition.getLegsPosition()
                    && item.getItemPosition().getNeckPosition() <= itemPosition.getNeckPosition()
                    && item.getItemPosition().getCloakPosition() <= itemPosition.getCloakPosition()
                    && item.getItemPosition().getFootPosition() <= itemPosition.getFootPosition())
            {
                itemPosition.setBodyPosition(itemPosition.getBodyPosition() - item.getItemPosition().getBodyPosition());
                itemPosition.setFingerPosition(itemPosition.getFingerPosition() - item.getItemPosition().getFingerPosition());
                itemPosition.setHeadPosition(itemPosition.getHeadPosition() - item.getItemPosition().getHeadPosition());
                itemPosition.setHandPosition(itemPosition.getHandPosition() - item.getItemPosition().getHandPosition());
                itemPosition.setLegsPosition(itemPosition.getLegsPosition() - item.getItemPosition().getLegsPosition());
                itemPosition.setNeckPosition(itemPosition.getNeckPosition() - item.getItemPosition().getNeckPosition());
                itemPosition.setCloakPosition(itemPosition.getCloakPosition() - item.getItemPosition().getCloakPosition());
                itemPosition.setFootPosition(itemPosition.getFootPosition() - item.getItemPosition().getFootPosition());

                equippedItems.add(item);
                backpackItems.remove(item);
            }
        }
    }

    public void unequip(Item item) {
        if (equippedItems.contains(item)) {
            itemPosition.setBodyPosition(itemPosition.getBodyPosition() + item.getItemPosition().getBodyPosition());
            itemPosition.setFingerPosition(itemPosition.getFingerPosition() + item.getItemPosition().getFingerPosition());
            itemPosition.setHeadPosition(itemPosition.getHeadPosition() + item.getItemPosition().getHeadPosition());
            itemPosition.setHandPosition(itemPosition.getHandPosition() + item.getItemPosition().getHandPosition());
            itemPosition.setLegsPosition(itemPosition.getLegsPosition() + item.getItemPosition().getLegsPosition());
            itemPosition.setNeckPosition(itemPosition.getNeckPosition() + item.getItemPosition().getNeckPosition());
            itemPosition.setCloakPosition(itemPosition.getCloakPosition() + item.getItemPosition().getCloakPosition());
            itemPosition.setFootPosition(itemPosition.getFootPosition() + item.getItemPosition().getFootPosition());

            backpackItems.add(item);
            equippedItems.remove(item);
        }
    }

}
