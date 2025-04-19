package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.CreatureRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.DamageMultiplier;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.persistent.Item;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.model.persistent.embeddable.Skills;
import edu.nsu.dnd.repository.CreatureRepository;
import edu.nsu.dnd.service.CampaignService;
import edu.nsu.dnd.service.CreatureService;
import edu.nsu.dnd.service.ItemService;
import edu.nsu.dnd.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CreatureServiceImpl implements CreatureService {

    private final CreatureRepository creatureRepository;
    private final CampaignService campaignService;
    private final ItemService itemService;
    private final LocationService locationService;

    @Override
    public Creature get(Long id) {
        return creatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Creature with id " + id + " not found"));
    }

    @Override
    public List<Creature> getPageByCampaignId(Long campaignId) {
        return creatureRepository.findByCampaignId(campaignId);
    }

    @Override
    public List<Creature> getByLocationId(Long locationId) {
        return creatureRepository.findPageByCampaignId(locationId);
    }

    @Override
    public Page<Creature> getPageByCampaignId(Long campaignId, Pageable pageable) {
        return creatureRepository.findPageByCampaignId(campaignId, pageable);
    }

    @Override
    public Page<Creature> getPageByLocationId(Long locationId, Pageable pageable) {
        return creatureRepository.findPageByLocationId(locationId, pageable);
    }

    private void fillFields(CreatureRequest request, Creature creature) {
        if (request.getLocationId() != null) {
            creature.setLocation(locationService.get(request.getLocationId()));
        }
        else {
            creature.setLocation(null);
        }
        creature.setPosition(request.getPosition());
        creature.setOperational(request.isOperational());
        creature.setCurrentHp(request.getCurrentHp());
        creature.setMaxHp(request.getMaxHp());
        creature.setTemporaryHp(request.getTemporaryHp());
        creature.setArmorClass(request.getArmorClass());
        creature.setAbilities(request.getAbilities());
        creature.setDamageMultipliers(request.getDamageMultipliers());
        creature.setSize(request.getSize());
        creature.setRace(request.getRace());
        creature.setConditions(request.getConditions());
        creature.setSkills(request.getSkills());
        creature.setEquippedItems(new ArrayList<>());
        for (Long idItemResponse : request.getEquippedItemIds()) {
            Item item = itemService.get(idItemResponse);
            if (item.equip(creature.getEquippedItems(), creature.getMaxItemPosition())) {
                creature.equip(item);
            } else {
                throw new RuntimeException("Can't equip this items");
            }
        }
        creature.setBackpackItems(new ArrayList<>());
        for (Long idItemResponse : request.getBackpackItemIds()) {
            Item item = itemService.get(idItemResponse);
            creature.takeItem(item);
        }
    }

    @Override
    public Creature create(CreatureRequest request) {
        Creature creature = new Creature();
        creature.setCampaign(campaignService.get(request.getCampaignId()));
        fillFields(request, creature);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature update(Long id, CreatureRequest request) {
        Creature creature = get(id);
        fillFields(request, creature);
        return creatureRepository.save(creature);
    }

    @Override
    public void delete(Long id) {
        creatureRepository.deleteById(id);
    }

    @Override
    public Creature replace(Long id, Position position) {
        Creature creature = get(id);
        creature.setPosition(position);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature relocate(Long id, Long locationId) {
        Creature creature = get(id);
        creature.setLocation(locationService.get(locationId));
        Position position = new Position();
        creature.setPosition(position);
        return creatureRepository.save(creature);
    }

    @Override
    public int giveHit(Long id, int d20hit, Long itemId) {
        Creature creature = get(id);
        Item item = itemService.get(itemId);
        int abilityBonus = creature.getAbilities().getAbilityModifierValue(item.getAttackAbility());
        return d20hit + abilityBonus + item.getBonus() + creature.getProficiencyBonus();
    }

    @Override
    public Boolean takeHit(Long id, int hit) {
        Creature creature = get(id);
        return creature.getArmorClass() <= hit;
    }

    @Override
    public Creature damage(Long id, DamageRequest damageRequest) {
        Creature creature = get(id);
        if (creature.getDamageMultipliers().containsKey(damageRequest.getDamageType())) {
            switch (creature.getDamageMultipliers().get(damageRequest.getDamageType())) {
                case DamageMultiplier.IMMUNITY -> {
                    creature.inflictDamage(0, damageRequest.getCritical());
                }
                case DamageMultiplier.VULNERABILITY -> {
                    creature.inflictDamage(damageRequest.getDamage() * 2, damageRequest.getCritical());
                }
                case DamageMultiplier.RESISTANCE -> {
                    creature.inflictDamage(damageRequest.getDamage() / 2, damageRequest.getCritical());
                }
            }
        } else {
            creature.inflictDamage(damageRequest.getDamage(), damageRequest.getCritical());
        }
        return creatureRepository.save(creature);
    }

    @Override
    public Creature heal(Long id, int healAmount) {
        Creature creature = get(id);
        creature.healDamage(healAmount);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature resize(Long id, Size size) {
        Creature creature = get(id);
        creature.setSize(size);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature addItem(Long id, Long itemId) {
        Creature creature = get(id);
        Item item = itemService.get(itemId);
        creature.getBackpackItems().add(item);
        return creatureRepository.save(creature);
    }

    @Override
    public Creature removeItem(Long id, Long itemId) {
        Creature creature = get(id);
        Item item = itemService.get(itemId);
        creature.getBackpackItems().remove(item);
        creature.getEquippedItems().remove(item);
        return creatureRepository.save(creature);
    }

    @Override
    public SkillCheckResponse skillCheck(Long id, SkillCheckRequest skillCheckRequest) {
        Creature creature = get(id);
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

        if (creature.getSkills().contains(skillCheckRequest.getSkill())) {
            skillCheckResponse.setValue(
                    skillCheckRequest.getD20Value()
                            + (int) Math.floor(creature.getProficiencyBonus() * skillCheckRequest.getProficiencyBonusMultiplier())
                            + creature.getAbilities().getAbilityModifierValue(skillCheckRequest.getAbility()));
            skillCheckResponse.setChecked(null);
            return skillCheckResponse;
        }

        skillCheckResponse.setValue(
                skillCheckRequest.getD20Value()
                        + creature.getAbilities().getAbilityModifierValue(skillCheckRequest.getAbility()));
        skillCheckResponse.setChecked(null);
        return skillCheckResponse;
    }

}
