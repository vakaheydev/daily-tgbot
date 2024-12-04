package vaka.daily.tgbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import vaka.daily.tgbot.handler.MessageHandlerFactory;

import java.util.Objects;

@Configuration
@Import(TelegramBotConfig.class)
public class TelegramHandlerConfig {
    @Value("${bot_token}")
    private String botToken;

    @Bean
    public TelegramClient telegramClient() {
        Objects.requireNonNull(botToken);
        return new OkHttpTelegramClient(botToken);
    }
}
