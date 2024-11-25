package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.enums.Race;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
public abstract class Alive extends Destructible {

    @ManyToMany
    private List<Item> backpackItems = new ArrayList<>();

    @ManyToMany
    private List<Item> equippedItems = new ArrayList<>();

    @Embedded
    private Abilities abilities;

    @Enumerated(EnumType.STRING)
    private Race race;
    private int speed;

}
