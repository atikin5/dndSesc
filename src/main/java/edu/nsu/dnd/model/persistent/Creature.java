package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.enums.*;
import edu.nsu.dnd.model.persistent.embeddable.Abilities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Creature extends DestructibleObject {

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
