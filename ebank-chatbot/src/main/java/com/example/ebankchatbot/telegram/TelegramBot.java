package com.example.ebankchatbot.telegram;

import jakarta.annotation.PostConstruct;
import com.example.ebankchatbot.Agent.EbankAiAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.logging.Logger;

@Component
public class TelegramBot extends TelegramLongPollingBot {


    @Value("${telegram.token}")
    private String telegramBotToken;

    private final EbankAiAgent aiAgent;  // 👈 ajoute final

    public TelegramBot(EbankAiAgent aiAgent) {  // 👈 injection via constructeur
        this.aiAgent = aiAgent;
    }

    @PostConstruct
    public void registerTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            String response = aiAgent.chat(message);
            if (response.length() > 2000) {
                response = response.substring(0, 2000) + "...";
            }

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(chatId.toString())
                    .text(response)
                    .build();
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    // Envoyer un message texte
    private void sendText(Long chatId, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Indiquer que le bot est en train d'écrire
    private void sendTyping(Long chatId) {
        SendChatAction action = SendChatAction.builder()
                .chatId(chatId.toString())
                .action("typing")
                .build();
        try {
            execute(action);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "EbankAssistant_bot";
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }
}