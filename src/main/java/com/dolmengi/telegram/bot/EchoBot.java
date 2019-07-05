package com.dolmengi.telegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


/**
 * token : 799392642:AAHwoAXIQhSFor8e977zYdPGQS6YrMVdt9M
 * https://api.telegram.org/bot{token}/getMe
 * https://api.telegram.org/bot{token}/getUpdates
 */
@Component
@Slf4j
public class EchoBot extends TelegramLongPollingBot {

  @Value("${bot.username}")
  private String username;

  @Value("${bot.token}")
  private String token;

  @Override
  public String getBotUsername() {
    return username;
  }

  @Override
  public String getBotToken() {
    return token;
  }

  /**
   * update.getMessage().getFrom().getId()
   * update.getMessage().getFrom().getFirstName()
   * update.getMessage().getFrom().getLastName()
   * update.getMessage().getChatId() : 채팅방의 ID
   * update.getMessage().getText() : 받은 TEXT
   * update.getMessage().getReplyToMessage().getText() : bot이 물어 본 받은 TEXT 사용자
   */
  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      Message message = update.getMessage();
      SendMessage response = new SendMessage();
      Long chatId = message.getChatId();
      response.setChatId(chatId);
      String text = message.getText();
      response.setText(text);
      try {
        execute(response);
        log.info("Sent message \"{}\" to {}", text, chatId);
      } catch (TelegramApiException e) {
        log.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
      }
    }
  }
/*
  @PostConstruct
  public void init() {

  }
*/
}
