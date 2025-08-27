package com.chatApp.myChatAppBackend.config;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class WebSocketEventListner {

    // This method listens for WebSocket disconnection events
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // To do: Handle user disconnection logic here
        log.info("User Disconnected");
    }

    // Can add more event listeners for connection events or message events etc.
    // Just create methods and annotate them with @EventListener
    // and specify the event type in the method parameter
}
