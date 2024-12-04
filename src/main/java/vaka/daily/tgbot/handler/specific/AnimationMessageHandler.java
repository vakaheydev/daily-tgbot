package vaka.daily.tgbot.handler.specific;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import vaka.daily.tgbot.handler.AbstractMessageHandler;
import vaka.daily.tgbot.service.TelegramMessageService;

@Component
public class AnimationMessageHandler extends AbstractMessageHandler {
    public AnimationMessageHandler(TelegramMessageService telegramMessageService) {
        super(telegramMessageService);
    }

    @Override
    public void handle(Message message) {

    }
}
