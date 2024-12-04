package vaka.daily.tgbot.handler;

import org.telegram.telegrambots.meta.api.objects.message.Message;

public interface MessageHandler {
    void handle(Message message);
}
