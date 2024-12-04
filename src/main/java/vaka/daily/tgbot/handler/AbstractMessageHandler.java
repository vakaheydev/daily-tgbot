package vaka.daily.tgbot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import vaka.daily.tgbot.service.TelegramMessageService;

@Slf4j
@Component
public abstract class AbstractMessageHandler implements MessageHandler {

    private final TelegramMessageService service;

    public AbstractMessageHandler(TelegramMessageService telegramMessageService) {
        this.service = telegramMessageService;
    }

    protected void sendText(long chatId, String text) {
        service.sendText(chatId, text);
    }

    protected void sendSticker(long chatId, Sticker sticker) {
        service.sendSticker(chatId, sticker);
    }

    protected void sendAnimation(long chatId, Animation animation) {
        sendAnimation(chatId, animation);
    }
}
