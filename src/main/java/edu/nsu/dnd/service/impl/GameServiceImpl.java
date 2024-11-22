package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.persistent.Session;
import edu.nsu.dnd.repository.SessionRepository;
import edu.nsu.dnd.service.GameService;
import edu.nsu.dnd.service.WebSocketMessagingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final SessionRepository sessionRepository;
    private final WebSocketMessagingService webSocketMessagingService;

    @Override
    public Object moveCharacter(Long campaignId, Long characterId, Integer direction) {
        Session session = sessionRepository.findById(campaignId).orElseThrow(() -> new RuntimeException("session not found"));
        // Fetch from DB
        // Update state
        // Save DB
        sessionRepository.save(session);
        webSocketMessagingService.sendMessage(session.getCode(), Map.of("aaa", "bbbb"));
        return null;
    }
}
