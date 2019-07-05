package com.dolmengi.telegram.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Value("${bot.chatId}")
  private String chatId;

  @Value("${bot.token}")
  private String token;

  public boolean sendMessage(String message) {
    TelegramBot bot = new TelegramBot(token);
    SendMessage request = new SendMessage(chatId, message)
        .parseMode(ParseMode.HTML)
        .disableWebPagePreview(true)
        .disableNotification(false);

    SendResponse sendResponse = bot.execute(request);
    System.out.println(sendResponse.message());

    return sendResponse.isOk();
  }

}
