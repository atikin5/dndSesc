package edu.nsu.dnd.service;

import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;

import java.util.List;

public interface CreatureService {

    Creature get(Long id) ;

    List<Creature> getByCampaignId(Long campaignId);

    List<Creature> getByLocationId(Long locationId);

    Creature create(Creature creature);

    Creature update(Long id, Creature creature);

    void delete(Long id);

    Creature move(Long id, Position position);

    Creature relocate(Long id, Long locationId);

    Creature damage(Long id, Damage damage);

    Creature heal(Long id, int healAmount);

    Creature resize(Long id, Size size);

    Creature addItem(Long id, Long itemId);
}
