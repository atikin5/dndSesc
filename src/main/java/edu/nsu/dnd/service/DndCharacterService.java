package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.DndCharacterRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.enums.Ability;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.enums.Skill;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;

import java.util.List;

public interface DndCharacterService {

    DndCharacter get(Long id);

    List<DndCharacter> getByCampaignId(Long campaignId);

    List<DndCharacter> getByLocationId(Long locationId);

    DndCharacter create(DndCharacterRequest dndCharacterRequest);

    DndCharacter update(Long id, DndCharacterRequest dndCharacterRequest);

    void delete(Long id);

    DndCharacter move(Long id, Position position);

    DndCharacter relocate(Long id, Long locationId);

    DndCharacter damage(Long id, Damage damage);

    DndCharacter heal(Long id, int healAmount);

    DndCharacter resize(Long id, Size size);

    DndCharacter addItem(Long id, Long itemId);

    DndCharacter removeItem(Long id, Long itemId);

    SkillCheckResponse skillCheck(Long id, SkillCheckRequest skillCheckRequest);


}
