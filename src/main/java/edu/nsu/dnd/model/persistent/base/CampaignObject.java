package edu.nsu.dnd.model.persistent.base;

import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Setter
@MappedSuperclass
public abstract class CampaignObject extends AbstractPersistable<Long> {

    /**
     * То, как будет отрисовываться на карте
     */
    private String type;

    @Embedded
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Campaign campaign;
}
