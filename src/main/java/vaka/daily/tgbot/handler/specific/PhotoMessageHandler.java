package vaka.daily.tgbot.handler.specific;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import vaka.daily.tgbot.handler.AbstractMessageHandler;
import vaka.daily.tgbot.service.TelegramMessageService;

@Component
public class PhotoMessageHandler extends AbstractMessageHandler {
    public PhotoMessageHandler(TelegramMessageService telegramMessageService) {
        super(telegramMessageService);
    }

    @Override
    public void handle(Message message) {

    }
}
