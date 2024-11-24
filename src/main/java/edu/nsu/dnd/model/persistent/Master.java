package edu.nsu.dnd.model.persistent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Master extends AbstractPersistable<Long> {

    private String username;

    @Column(name = "pass")
    private String password;

    private String email;
}
