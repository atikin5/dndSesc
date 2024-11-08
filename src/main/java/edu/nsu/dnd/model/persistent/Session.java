package edu.nsu.dnd.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Session extends AbstractPersistable<Long> {

    private String code;

    @ManyToOne
    private Master master;

    private String state;

    private Date createDate;

    private Date startDate;

    @OneToMany(mappedBy = "session")
    private List<Character> characters = new ArrayList<>();
}
