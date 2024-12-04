package vaka.daily.tgbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vaka.daily.tgbot.core.TelegramBot;
import vaka.daily.tgbot.handler.MessageHandlerFactory;

@Configuration
public class TelegramBotConfig {
    @Value("${bot_token}")
    private String botToken;

    private final MessageHandlerFactory handlerFactory;

    public TelegramBotConfig(MessageHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(handlerFactory);
    }

    @Bean
    public TelegramBotsLongPollingApplication telegramBotsLongPollingApplication() {
        TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();

        try {
            botsApplication.registerBot(botToken, telegramBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        return botsApplication;
    }
}
