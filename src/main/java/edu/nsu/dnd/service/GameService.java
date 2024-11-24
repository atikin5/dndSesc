package edu.nsu.dnd.service;

import edu.nsu.dnd.model.persistent.embeddable.Position;

import java.util.List;

public interface GameService {

    Object moveCharacter(Long campaignId, Long characterId, List<Position> path);
}
