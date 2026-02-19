package com.example.ebankchatbot.ChatController;


import com.example.ebankchatbot.Agent.EbankAiAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {


    private EbankAiAgent ebankAiAgent;

    public ChatbotController(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String message) {
        return ebankAiAgent.chat(message);
    }
}