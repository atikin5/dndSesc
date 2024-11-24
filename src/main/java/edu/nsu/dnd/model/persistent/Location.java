package edu.nsu.dnd.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Location extends AbstractPersistable<Long> {

    @ManyToOne
    private Campaign campaign;

    @OneToMany(mappedBy = "location")
    private List<DndCharacter> dndCharacters = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<Creature> creatures = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<DestructibleObject> destructibleObjects = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<Tile> tiles = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<Wall> walls = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<Door> doors = new ArrayList<>();
}
