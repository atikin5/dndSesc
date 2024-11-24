package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.enums.CampaignStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Campaign extends AbstractPersistable<Long> {

    private String code;

    @Enumerated(EnumType.STRING)
    private CampaignStatus status;
    private String title;

    @OneToMany(mappedBy = "campaign")
    private List<DndCharacter> dndCharacters = new ArrayList<>();

    @OneToMany(mappedBy = "campaign")
    private List<Creature> creatures = new ArrayList<>();

    @OneToMany(mappedBy = "campaign")
    private List<DestructibleObject> destructibleObjects = new ArrayList<>();

    @OneToMany(mappedBy = "campaign")
    private List<Location> locations = new ArrayList<>();

    public Campaign(String title) {
        this.title = title;
        status= CampaignStatus.DRAFT;
        createdAt = new Date();
    }

    private Date createdAt;
    private Date startedAt;
    private Date completedAt;
}
