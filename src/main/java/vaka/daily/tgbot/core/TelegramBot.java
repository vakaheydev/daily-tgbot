package vaka.daily.tgbot.core;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import vaka.daily.tgbot.handler.MessageHandlerFactory;

@Slf4j
public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
    MessageHandlerFactory handlerFactory;

    public TelegramBot(MessageHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage()) {
            handlerFactory.getInstance(update).handle(update.getMessage());
        } else {
            throw new IllegalArgumentException("There is no handler for update without message");
        }
    }
}
