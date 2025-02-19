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
    private ItemPosition maxItemPosition = new ItemPosition(2, 10, 1, 1, 1, 1, 1, 1);

    @ManyToMany
    private List<Item> backpackItems = new ArrayList<>();

    @ManyToMany
    private List<Item> equippedItems = new ArrayList<>();

    @Embedded
    private Abilities abilities = new Abilities(10, 10, 10, 10, 10, 10);

    @Enumerated(EnumType.STRING)
    private Race race = Race.HUMAN;

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
        equippedItems.add(item);
    }

    public void unequip(Item item) {
        equippedItems.remove(item);
    }

    public void takeItem(Item item) {
        backpackItems.add(item);
    }

    public void removeItem(Item item) {
        backpackItems.remove(item);
    }

}
