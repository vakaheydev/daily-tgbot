package vaka.daily.tgbot.handler.specific;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import vaka.daily.tgbot.handler.AbstractMessageHandler;
import vaka.daily.tgbot.service.TelegramMessageService;

@Component
public class StickerMessageHandler extends AbstractMessageHandler {
    public StickerMessageHandler(TelegramMessageService telegramMessageService) {
        super(telegramMessageService);
    }

    @Override
    public void handle(Message message) {
        Sticker sticker = message.getSticker();
        sendText(message.getChatId(), "Классный стикер! :)");
        sendSticker(message.getChatId(), sticker);
    }
}
