package edu.nsu.dnd.service;

public interface WebSocketMessagingService {

    void sendMessage(String sessionNumber, Object message);
}
