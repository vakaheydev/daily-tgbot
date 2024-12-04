package vaka.daily.tgbot.handler.specific;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import vaka.daily.tgbot.handler.AbstractMessageHandler;
import vaka.daily.tgbot.service.MessageHandlerService;
import vaka.daily.tgbot.service.TelegramMessageService;

import static vaka.daily.tgbot.core.update.MessageType.TEXT;

@Component
public class TextMessageHandler extends AbstractMessageHandler {
    MessageHandlerService messageHandlerService;

    public TextMessageHandler(TelegramMessageService telegramMessageService,
                              MessageHandlerService messageHandlerService) {
        super(telegramMessageService);
        this.messageHandlerService = messageHandlerService;
    }

    @Override
    public void handle(Message message) {
        String textResponse = messageHandlerService.createResponse(TEXT, message);
        sendText(message.getChatId(), textResponse);
    }
}
