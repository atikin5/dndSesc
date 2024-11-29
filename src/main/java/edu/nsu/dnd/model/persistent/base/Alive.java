package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.enums.Race;
import edu.nsu.dnd.model.enums.Skill;
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
    ItemPosition maxItemPosition = new ItemPosition(2, 10, 1, 1, 1, 1, 1, 1);

    @ManyToMany
    private List<Item> backpackItems = new ArrayList<>();

    @ManyToMany
    private List<Item> equippedItems = new ArrayList<>();

    @Embedded
    private Abilities abilities;

    @Enumerated(EnumType.STRING)
    private Race race;
    private int speed;

    private int proficiencyBonus;
    private Skills skills = new Skills();

}
