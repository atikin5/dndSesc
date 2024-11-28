package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.DestructibleObjectRequest;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DestructibleObject;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.DestructibleObjectRepository;
import edu.nsu.dnd.service.DestructibleObjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DestructibleObjectServiceImpl implements DestructibleObjectService {

    private final DestructibleObjectRepository destructibleObjectRepository;

    @Override
    public DestructibleObject get(Long id) {
        return destructibleObjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DestructibleObject with id " + id + " not found"));
    }

    @Override
    public DestructibleObject create(DestructibleObjectRequest destructibleObjectRequest) {
        DestructibleObject destructibleObject = new DestructibleObject();
        destructibleObject.setType(destructibleObjectRequest.getType());
        destructibleObject.setCampaign(destructibleObjectRequest.getCampaign());
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public DestructibleObject update(Long id, DestructibleObjectRequest destructibleObjectRequest) {
        DestructibleObject destructibleObject = get(id);
        destructibleObject.setType(destructibleObjectRequest.getType());
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public List<DestructibleObject> getDestructibleObjectsByLocationId(Long locationId) {
        return destructibleObjectRepository.findByLocationId(locationId);
    }

    @Override
    public List<DestructibleObject> getDestructibleObjectsByCampaignId(Long campaignId) {
        return destructibleObjectRepository.findByCampaignId(campaignId);
    }

    @Override
    public void delete(Long id) {
        DestructibleObject destructibleObject = get(id);
        destructibleObjectRepository.delete(destructibleObject);
    }

    @Override
    public DestructibleObject replace(Long id, Position position) {
        DestructibleObject destructibleObject = get(id);
        destructibleObject.setPosition(position);
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public DestructibleObject damage(Long id, Damage damage) {
        DestructibleObject destructibleObject = get(id);
        if (destructibleObject.getDamageMultipliers().containsKey(damage.getDamageType())) {
            switch (destructibleObject.getDamageMultipliers().get(damage.getDamageType())) {
                case DamageMultiplier.IMMUNITY -> {
                    destructibleObject.inflictDamage(0, damage.getCritical());
                }
                case DamageMultiplier.VULNERABILITY -> {
                    destructibleObject.inflictDamage(damage.getDamage() * 2, damage.getCritical());
                }
                case DamageMultiplier.RESISTANCE -> {
                    destructibleObject.inflictDamage(damage.getDamage() / 2, damage.getCritical());
                }
            }
        } else {
            destructibleObject.inflictDamage(damage.getDamage(), damage.getCritical());
        }
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public DestructibleObject heal(Long id, int healAmount) {
        DestructibleObject destructibleObject = get(id);
        destructibleObject.healDamage(healAmount);
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public DestructibleObject resize(Long id, Size size) {
        DestructibleObject destructibleObject = get(id);
        destructibleObject.setSize(size);
        return destructibleObjectRepository.save(destructibleObject);
    }
}
