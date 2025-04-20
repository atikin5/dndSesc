package edu.nsu.dnd.controller;


import edu.nsu.dnd.service.WebSocketMessagingService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Controller
public class WebSocketController {

    private final WebSocketMessagingService messagingService;

//    @MessageMapping("/{sessionNumber}")
//    public void connect(@DestinationVariable String sessionNumber, Map<String, Object> message) {
//        messagingService.sendMessage(sessionNumber, Map.of("message", "Hi " + sessionNumber));
//        displayService.get(serialNumber).ifPresentOrElse(
//                display -> {
//                    websocketMessageService.sendCommand(serialNumber, new LocationCommand(display));
//                    websocketMessageService.sendCommand(serialNumber, new MainCommand(display));
//                    if (display.getProductLine() != null) {
//                        websocketMessageService.sendCommand(serialNumber, new ManifestCommand(display));
//                    }
//                },
//                () -> websocketMessageService.sendCommand(serialNumber, new NotRegisteredCommand()));
//    }
    @MessageMapping("/{sessionNumber}")
    public void receiveMessage(@DestinationVariable String sessionNumber) {
        messagingService.sendMessage(sessionNumber, "aaaa");
    }

}
