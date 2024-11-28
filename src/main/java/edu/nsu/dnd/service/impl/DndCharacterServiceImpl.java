package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.DndCharacterRequest;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.repository.DndCharacterRepository;
import edu.nsu.dnd.service.DndCharacterService;
import edu.nsu.dnd.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DndCharacterServiceImpl implements DndCharacterService {
    DndCharacterRepository dndCharacterRepository;
    LocationService locationService;

    @Override
    public DndCharacter get(Long id) {
        return dndCharacterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DndCharacter with id " + id + " not found"));
    }

    @Override
    public List<DndCharacter> getAllByCampaignId(Long campaignId) {
        return dndCharacterRepository.findByCampaignId(campaignId);
    }

    @Override
    public List<DndCharacter> getAllByLocationId(Long locationId) {
        return dndCharacterRepository.findByLocationId(locationId);
    }

    @Override
    public DndCharacter create(DndCharacterRequest dndCharacterRequest) {
        DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setType(dndCharacterRequest.getType());
        dndCharacter.setAbilities(dndCharacterRequest.getAbilities());
        dndCharacter.setCampaign(dndCharacterRequest.getCampaign());
        dndCharacter.setRace(dndCharacterRequest.getRace());
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter update(Long id, DndCharacterRequest dndCharacterRequest) {
        DndCharacter dndCharacter = get(id);
        dndCharacter.setAbilities(dndCharacterRequest.getAbilities());
        dndCharacter.setCampaign(dndCharacterRequest.getCampaign());
        dndCharacter.setRace(dndCharacterRequest.getRace());
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public void delete(Long id) {
        dndCharacterRepository.deleteById(id);
    }

    @Override
    public DndCharacter replace(Long id, Position position) {
        DndCharacter dndCharacter = get(id);
        dndCharacter.setPosition(position);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter damage(Long id, Damage damage) {
        DndCharacter dndCharacter = get(id);
        if (dndCharacter.getDamageMultipliers().containsKey(damage.getDamageType())) {
            switch (dndCharacter.getDamageMultipliers().get(damage.getDamageType())) {
                case DamageMultiplier.IMMUNITY -> {
                    dndCharacter.inflictDamage(0, damage.getCritical());
                }
                case DamageMultiplier.VULNERABILITY -> {
                    dndCharacter.inflictDamage(damage.getDamage() * 2, damage.getCritical());
                }
                case DamageMultiplier.RESISTANCE -> {
                    dndCharacter.inflictDamage(damage.getDamage() /2, damage.getCritical());
                }
            }
        } else {
            dndCharacter.inflictDamage(damage.getDamage(), damage.getCritical());
        }
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter heal(Long id, int healAmount) {
        DndCharacter dndCharacter = get(id);
        dndCharacter.healDamage(healAmount);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter resize(Long id, Size size) {
        DndCharacter dndCharacter = get(id);
        dndCharacter.setSize(size);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter relocate(Long id, Long locationId) {
        DndCharacter dndCharacter = get(id);
        dndCharacter.setLocation(locationService.get(locationId));
        return dndCharacterRepository.save(dndCharacter);
    }
}
