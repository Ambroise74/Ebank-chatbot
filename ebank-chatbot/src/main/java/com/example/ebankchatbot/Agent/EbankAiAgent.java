package com.example.ebankchatbot.Agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

    @Service
    public class EbankAiAgent {

        private final ChatClient chatClient;

        // 👇 le nom du constructeur DOIT être identique au nom de la classe
        public EbankAiAgent(ChatClient.Builder builder, ChatMemory chatMemory, ToolCallbackProvider toolCallbackProvider) {
            this.chatClient = builder
                    .defaultSystem("Tu es un assistant bancaire. Tu aides les utilisateurs avec leurs comptes et opérations bancaires.")
                    .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                    .defaultToolCallbacks(toolCallbackProvider)
                    .build();
        }

        public String chat(String message) {
            return chatClient.prompt()
                    .user(message)
                    .call()
                    .content();
        }
    }

