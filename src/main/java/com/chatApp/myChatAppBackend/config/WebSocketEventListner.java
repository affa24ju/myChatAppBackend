package com.chatApp.myChatAppBackend.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chatApp.myChatAppBackend.model.ChatMessage;
import com.chatApp.myChatAppBackend.model.MessageType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class WebSocketEventListner {

    private final SimpMessageSendingOperations messagingTemplate;

    // This method listens for WebSocket disconnection events
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // Retrieve the username from the session attributes
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("User Disconnected : {}", username);
            // Create a chat message to inform others that the user has left
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);

        }
    }

}

// Can add more event listeners for connection events or message events etc.
// Just create methods and annotate them with @EventListener
// and specify the event type in the method parameter
