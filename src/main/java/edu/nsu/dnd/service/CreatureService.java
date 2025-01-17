package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.CreatureRequest;
import edu.nsu.dnd.model.dto.requests.SkillCheckRequest;
import edu.nsu.dnd.model.dto.responses.SkillCheckResponse;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.Creature;
import edu.nsu.dnd.model.dto.requests.DamageRequest;
import edu.nsu.dnd.model.persistent.embeddable.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CreatureService {

    Creature get(Long id) ;

    List<Creature> getPageByCampaignId(Long campaignId);

    List<Creature> getByLocationId(Long locationId);

    Page<Creature> getPageByCampaignId(Long campaignId, Pageable pageable);

    Page<Creature> getPageByLocationId(Long locationId, Pageable pageable);

    Creature create(CreatureRequest request);

    Creature update(Long id, CreatureRequest request);

    void delete(Long id);

    Creature replace(Long id, Position position);

    Creature relocate(Long id, Long locationId);

    int giveHit(Long id, int d20hit, Long itemId);

    Boolean takeHit(Long id, int hit);

    Creature damage(Long id, DamageRequest damageRequest);

    Creature heal(Long id, int healAmount);

    Creature resize(Long id, Size size);

    Creature addItem(Long id, Long itemId);

    Creature removeItem(Long id, Long itemId);

    SkillCheckResponse skillCheck(Long id, SkillCheckRequest skillCheckRequest);
}
