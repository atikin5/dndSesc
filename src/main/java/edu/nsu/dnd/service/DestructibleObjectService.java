package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.DestructibleObjectRequest;
import edu.nsu.dnd.model.enums.DamageType;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.persistent.DestructibleObject;
import edu.nsu.dnd.model.persistent.embeddable.Damage;
import edu.nsu.dnd.model.persistent.embeddable.Position;

import java.util.List;

public interface DestructibleObjectService {
    DestructibleObject get(Long id);

    DestructibleObject create(DestructibleObjectRequest destructibleObjectRequest);

    DestructibleObject update(Long id, DestructibleObjectRequest destructibleObjectRequest);

    List<DestructibleObject> getDestructibleObjectsByLocationId(Long locationId);

    List<DestructibleObject> getDestructibleObjectsByCampaignId(Long campaignId);

    void delete(Long id);

    DestructibleObject replace(Long id, Position position);

    DestructibleObject damage(Long id, Damage damage);

    DestructibleObject heal(Long id, int healAmount);

    DestructibleObject resize(Long id, Size size);


}
