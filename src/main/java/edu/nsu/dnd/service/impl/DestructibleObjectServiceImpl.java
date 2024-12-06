package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.DestructibleObjectRequest;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DestructibleObject;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.DestructibleObjectRepository;
import edu.nsu.dnd.service.CampaignService;
import edu.nsu.dnd.service.DestructibleObjectService;
import edu.nsu.dnd.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DestructibleObjectServiceImpl implements DestructibleObjectService {

    DestructibleObjectRepository destructibleObjectRepository;
    CampaignService campaignService;
    LocationService locationService;

    @Override
    public DestructibleObject get(Long id) {
        return destructibleObjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DestructibleObject with id " + id + " not found"));
    }

    @Override
    public DestructibleObject create(DestructibleObjectRequest destructibleObjectRequest) {
        DestructibleObject destructibleObject = new DestructibleObject();
        destructibleObject.setType(destructibleObjectRequest.getType());
        destructibleObject.setCampaign(campaignService.get(destructibleObjectRequest.getCampaignId()));
        // TODO Dimensions?
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public DestructibleObject update(Long id, DestructibleObjectRequest destructibleObjectRequest) {
        DestructibleObject destructibleObject = get(id);
        destructibleObject.setType(destructibleObjectRequest.getType());
        // TODO Other fields?
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public List<DestructibleObject> getByLocationId(Long locationId) {
        return destructibleObjectRepository.findByLocationId(locationId);
    }

    @Override
    public List<DestructibleObject> getByCampaignId(Long campaignId) {
        return destructibleObjectRepository.findByCampaignId(campaignId);
    }

    @Override
    public void delete(Long id) {
        DestructibleObject destructibleObject = get(id);
        destructibleObjectRepository.delete(destructibleObject);
    }

    @Override
    public DestructibleObject move(Long id, Position position) {
        DestructibleObject destructibleObject = get(id);
        destructibleObject.setPosition(position);
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public DestructibleObject relocate(Long id, Long locationId) {
        DestructibleObject destructibleObject = get(id);
        destructibleObject.setLocation(locationService.get(locationId));
        Position position = new Position();
        destructibleObject.setPosition(position);
        return destructibleObjectRepository.save(destructibleObject);
    }

    @Override
    public Boolean takeHit(Long id, int hit) {
        DestructibleObject destructibleObject = get(id);
        return destructibleObject.getArmorClass() <= hit;
    }

    @Override
    public DestructibleObject damage(Long id, DamageRequest damageRequest) {
        DestructibleObject destructibleObject = get(id);
        if (destructibleObject.getDamageMultipliers().containsKey(damageRequest.getDamageType())) {
            switch (destructibleObject.getDamageMultipliers().get(damageRequest.getDamageType())) {
                case DamageMultiplier.IMMUNITY -> {
                    destructibleObject.inflictDamage(0, damageRequest.getCritical());
                }
                case DamageMultiplier.VULNERABILITY -> {
                    destructibleObject.inflictDamage(damageRequest.getDamage() * 2, damageRequest.getCritical());
                }
                case DamageMultiplier.RESISTANCE -> {
                    destructibleObject.inflictDamage(damageRequest.getDamage() / 2, damageRequest.getCritical());
                }
            }
        } else {
            destructibleObject.inflictDamage(damageRequest.getDamage(), damageRequest.getCritical());
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
