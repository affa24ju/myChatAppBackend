package com.chatApp.myChatAppBackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chatApp.myChatAppBackend.model.ChatMessage;

@Controller
public class ChatController {

    // Two methods: add user to chat and send message to chat
    // addUser to inform all that a new user has joined
    // sendMessage to send message to all users in the chat

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
