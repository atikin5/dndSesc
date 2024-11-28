package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.DndCharacterRequest;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DndCharacter;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;

import java.util.List;

public interface DndCharacterService {

    DndCharacter get(Long id);

    List<DndCharacter> getAllByCampaignId(Long campaignId);

    List<DndCharacter> getAllByLocationId(Long locationId);

    DndCharacter create(DndCharacterRequest dndCharacterRequest);

    DndCharacter update(Long id, DndCharacterRequest dndCharacterRequest);

    void delete(Long id);

    DndCharacter replace(Long id, Position position);

    DndCharacter damage(Long id, Damage damage);

    DndCharacter heal(Long id, int healAmount);

    DndCharacter resize(Long id, Size size);

    DndCharacter relocate(Long id, Long locationId);

}
