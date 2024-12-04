package vaka.daily.tgbot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import vaka.daily.tgbot.core.update.MessageType;
import vaka.daily.tgbot.handler.specific.*;

import java.util.HashMap;
import java.util.Map;

import static vaka.daily.tgbot.core.update.MessageType.*;

@Component
@Slf4j
public class MessageHandlerFactory {
    TelegramClient telegramClient;
    private final Map<MessageType, MessageHandler> handlers;

    public MessageHandlerFactory(
            TelegramClient telegramClient,
            TextMessageHandler textMessageHandler,
            CommandMessageHandler commandMessageHandler,
            StickerMessageHandler stickerMessageHandler,
            AnimationMessageHandler animationMessageHandler,
            PhotoMessageHandler photoMessageHandler
    ) {
        this.telegramClient = telegramClient;
        handlers = Map.of(
                TEXT, textMessageHandler,
                COMMAND, commandMessageHandler,
                STICKER, stickerMessageHandler,
                ANIMATION, animationMessageHandler,
                PHOTO, photoMessageHandler
        );
    }

    public MessageHandler getInstance(Update update) {
        MessageType msgType = resolveMessageType(update);
        log.debug("Resolved message type: {}", msgType.getName());
        MessageHandler messageHandler = resolveMessageHandler(msgType);
        log.debug("Resolved message handler: {}", messageHandler.getClass().getSimpleName());
        log.debug("Update from chat id: {}", update.getMessage().getChatId());
        return messageHandler;
    }

    private MessageType resolveMessageType(Update update) {
        Message message = update.getMessage();

        if (message == null) {
            return UNDEFINED;
        }

        if (message.hasText()) {
            String text = message.getText();

            if (isCommand(text)) {
                return COMMAND;
            } else {
                return TEXT;
            }
        } else if (message.hasSticker()) {
            return STICKER;
        } else if (message.hasAnimation()) {
            return ANIMATION;
        } else if (message.hasPhoto()) {
            return PHOTO;
        }

        return UNDEFINED;
    }

    private boolean isCommand(String text) {
        return text.startsWith("/");
    }

    private MessageHandler resolveMessageHandler(MessageType messageType) {
        MessageHandler handler = handlers.get(messageType);

        if (handler == null) {
            throw new IllegalArgumentException("No handler found for message type: " + messageType);
        }

        return handler;
    }
}
