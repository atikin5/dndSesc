package edu.nsu.dnd.service;

public interface GameService {

    Object moveCharacter(Long sessionId, Long characterId, Integer direction);
}
