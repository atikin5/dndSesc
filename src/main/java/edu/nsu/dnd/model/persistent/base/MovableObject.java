package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.persistent.Location;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class MovableObject extends CampaignObject {

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;


    public void changeLocation(Location location) {
        this.location = location;
    }
}
