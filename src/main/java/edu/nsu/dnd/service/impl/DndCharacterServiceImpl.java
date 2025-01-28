package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.DndCharacterRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.repository.DndCharacterRepository;
import edu.nsu.dnd.service.CampaignService;
import edu.nsu.dnd.service.DndCharacterService;
import edu.nsu.dnd.service.ItemService;
import edu.nsu.dnd.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DndCharacterServiceImpl implements DndCharacterService {

    private final DndCharacterRepository dndCharacterRepository;
    private final CampaignService campaignService;
    private final LocationService locationService;
    private final ItemService itemService;

    @Override
    public DndCharacter get(Long id) {
        return dndCharacterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DndCharacter with id " + id + " not found"));
    }

    @Override
    public List<DndCharacter> getByCampaignId(Long campaignId) {
        return dndCharacterRepository.findPageByCampaignId(campaignId);
    }

    @Override
    public List<DndCharacter> getByLocationId(Long locationId) {
        return dndCharacterRepository.findByLocationId(locationId);
    }

    @Override
    public Page<DndCharacter> getPageByCampaignId(Long campaignId, Pageable pageable) {
        return dndCharacterRepository.findPageByCampaignId(campaignId, pageable);
    }

    @Override
    public Page<DndCharacter> getPageByLocationId(Long locationId, Pageable pageable) {
        return dndCharacterRepository.findPageByLocationId(locationId, pageable);
    }

    @Override
    public DndCharacter create(DndCharacterRequest request) {
        DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setCampaign(campaignService.get(request.getCampaignId()));
        fillFields(request, dndCharacter);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter update(Long id, DndCharacterRequest request) {
        DndCharacter dndCharacter = get(id);
        fillFields(request, dndCharacter);
        return dndCharacterRepository.save(dndCharacter);
    }

    private void fillFields(DndCharacterRequest request, DndCharacter dndCharacter) {
        if (request.getLocationId() != null) {
            dndCharacter.setLocation(locationService.get(request.getLocationId()));
        }
        else {
            dndCharacter.setLocation(null);
        }
        dndCharacter.setType(request.getType());
        dndCharacter.setPosition(request.getPosition());
        dndCharacter.setOperational(request.isOperational());
        dndCharacter.setCurrentHp(request.getCurrentHp());
        dndCharacter.setMaxHp(request.getMaxHp());
        dndCharacter.setTemporaryHp(request.getTemporaryHp());
        dndCharacter.setArmorClass(request.getArmorClass());
        dndCharacter.setAbilities(request.getAbilities());
        dndCharacter.setDamageMultipliers(request.getDamageMultipliers());
        dndCharacter.setSize(request.getSize());
        dndCharacter.setRace(request.getRace());
        dndCharacter.setConditions(request.getConditions());
        dndCharacter.setCharacterClasses(request.getCharacterClasses());
        dndCharacter.setCharacterDescription(request.getCharacterDescription());
        dndCharacter.setReviveSuccesses(request.getReviveSuccesses());
        dndCharacter.setReviveFailures(request.getReviveFailures());

    }

    @Override
    public void delete(Long id) {
        dndCharacterRepository.deleteById(id);
    }

    @Override
    public DndCharacter move(Long id, Position position) {
        DndCharacter dndCharacter = get(id);
        dndCharacter.setPosition(position);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter relocate(Long id, Long locationId) {
        DndCharacter dndCharacter = get(id);
        dndCharacter.setLocation(locationService.get(locationId));
        Position position = new Position();
        dndCharacter.setPosition(position);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public int giveHit(Long id, int d20hit, Long itemId) {
        DndCharacter dndCharacter = get(id);
        Item item = itemService.get(itemId);
        int abilityBonus = dndCharacter.getAbilities().getAbilityModifierValue(item.getAttackAbility());
        return d20hit + abilityBonus + item.getBonus() + dndCharacter.getProficiencyBonus();

    }

    @Override
    public Boolean takeHit(Long id, int hit) {
        DndCharacter dndCharacter = get(id);
        return dndCharacter.getArmorClass() <= hit;
    }

    @Override
    public DndCharacter damage(Long id, DamageRequest damageRequest) {
        DndCharacter dndCharacter = get(id);
        if (dndCharacter.getDamageMultipliers().containsKey(damageRequest.getDamageType())) {
            switch (dndCharacter.getDamageMultipliers().get(damageRequest.getDamageType())) {
                case DamageMultiplier.IMMUNITY -> {
                    dndCharacter.inflictDamage(0, damageRequest.getCritical());
                }
                case DamageMultiplier.VULNERABILITY -> {
                    dndCharacter.inflictDamage(damageRequest.getDamage() * 2, damageRequest.getCritical());
                }
                case DamageMultiplier.RESISTANCE -> {
                    dndCharacter.inflictDamage(damageRequest.getDamage() / 2, damageRequest.getCritical());
                }
            }
        } else {
            dndCharacter.inflictDamage(damageRequest.getDamage(), damageRequest.getCritical());
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
    public DndCharacter addItem(Long id, Long itemId) {
        DndCharacter dndCharacter = get(id);
        Item item = itemService.get(itemId);
        dndCharacter.getBackpackItems().add(item);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public DndCharacter removeItem(Long id, Long itemId) {
        DndCharacter dndCharacter = get(id);
        Item item = itemService.get(itemId);
        dndCharacter.getBackpackItems().remove(item);
        dndCharacter.getEquippedItems().remove(item);
        return dndCharacterRepository.save(dndCharacter);
    }

    @Override
    public SkillCheckResponse skillCheck(Long id, SkillCheckRequest skillCheckRequest) {
        DndCharacter dndCharacter = get(id);
        SkillCheckResponse skillCheckResponse = new SkillCheckResponse();

        if (skillCheckRequest.getD20Value() == 1) {
            skillCheckResponse.setValue(1);
            skillCheckResponse.setChecked(false);
            return skillCheckResponse;
        }

        if (skillCheckRequest.getD20Value() == 20) {
            skillCheckResponse.setValue(20);
            skillCheckResponse.setChecked(true);
            return skillCheckResponse;
        }

        if (dndCharacter.getSkills().getProficiencySkills().contains(skillCheckRequest.getSkill())) {
            skillCheckResponse.setValue(
                    skillCheckRequest.getD20Value()
                            + (int) Math.floor(dndCharacter.getProficiencyBonus() * skillCheckRequest.getProficiencyBonusMultiplier())
                            + dndCharacter.getAbilities().getAbilityModifierValue(skillCheckRequest.getAbility()));
            skillCheckResponse.setChecked(null);
            return skillCheckResponse;
        }

        skillCheckResponse.setValue(
                skillCheckRequest.getD20Value()
                        + dndCharacter.getAbilities().getAbilityModifierValue(skillCheckRequest.getAbility()));
        skillCheckResponse.setChecked(null);
        return skillCheckResponse;
    }
}
