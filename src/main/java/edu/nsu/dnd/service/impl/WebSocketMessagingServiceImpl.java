package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.service.WebSocketMessagingService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WebSocketMessagingServiceImpl implements WebSocketMessagingService {

    SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(String sessionNumber, Object message) {
        messagingTemplate.convertAndSend("/topic/" + sessionNumber, message);

    }

}
