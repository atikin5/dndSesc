package edu.nsu.dnd.service;

public interface GameService {

    Object moveCharacter(Long campaignId, Long characterId, Integer direction);
}
