package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.persistent.Location;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Объект, который нельзя перемещать из локации.
 */
@Setter
@Getter
@MappedSuperclass
public abstract class LocationObject extends CampaignObject {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Location location;
}
