package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.CreatureRepository;
import edu.nsu.dnd.service.CreatureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CreatureServiceImpl implements CreatureService {

    private final CreatureRepository creatureRepository;

    @Override
    public Creature get(Long id) {
        return null;
    }

    @Override
    public List<Creature> getByCampaignId(Long campaignId) {
        return List.of();
    }

    @Override
    public List<Creature> getByLocationId(Long locationId) {
        return List.of();
    }

    @Override
    public Creature create(Creature creature) {
        return null;
    }

    @Override
    public Creature update(Long id, Creature creature) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Creature move(Long id, Position position) {
        return null;
    }

    @Override
    public Creature relocate(Long id, Long locationId) {
        return null;
    }

    @Override
    public Creature damage(Long id, Damage damage) {
        return null;
    }

    @Override
    public Creature heal(Long id, int healAmount) {
        return null;
    }

    @Override
    public Creature resize(Long id, Size size) {
        return null;
    }

    @Override
    public Creature addItem(Long id, Long itemId) {
        return null;
    }
}
