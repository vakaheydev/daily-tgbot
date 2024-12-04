package vaka.daily.tgbot.service;

import com.vaka.daily_client.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Optional;

@Service
@Slf4j
public class TelegramMessageService {

    private final TelegramClient telegramClient;
    private final UserService userService;

    public TelegramMessageService(TelegramClient telegramClient, UserService userService) {
        this.telegramClient = telegramClient;
        this.userService = userService;
    }

    public void sendText(long chatId, String text) {
        send(SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build()
        );
    }

    public void sendText(String login, String text) {
        User user = userService.getUserByLogin(login);

        send(SendMessage.builder()
                .chatId(user.getTelegramId())
                .text(text)
                .build()
        );
    }

    public void sendSticker(long chatId, Sticker sticker) {
        send(SendSticker.builder()
                .chatId(chatId)
                .sticker(new InputFile(sticker.getFileId()))
                .build()
        );
    }

    public void sendAnimation(long chatId, Animation animation) {
        send(SendAnimation.builder()
                .chatId(chatId)
                .animation(new InputFile(animation.getFileId()))
                .build()
        );
    }

    private void send(SendMessage sendMessage) {
        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }

    private void send(SendSticker sendSticker) {
        try {
            telegramClient.execute(sendSticker);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }

    private void send(SendAnimation sendAnimation) {
        try {
            telegramClient.execute(sendAnimation);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }
}
