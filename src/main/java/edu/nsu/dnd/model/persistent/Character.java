package edu.nsu.dnd.model.persistent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Getter
@Setter
public class Character extends AbstractPersistable<Long> {

    private String name;

    private String race;

    @Column(name = "class")
    private String characterClass;

    @ManyToOne
    private Session session;
}
