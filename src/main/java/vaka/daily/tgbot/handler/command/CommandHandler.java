package vaka.daily.tgbot.handler.command;

import org.telegram.telegrambots.meta.api.objects.message.Message;

public interface CommandHandler {
    String handle(Message message, String command, String[] args);
}
