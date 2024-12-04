package vaka.daily.tgbot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@Slf4j
@SpringBootApplication
public class TelegramBotApplication {
    @Value("${bot_token}")
    private String botToken;

    public static void main(String[] args) {
        var ctx = SpringApplication.run(TelegramBotApplication.class, args);

        var botsApplication = ctx.getBean(TelegramBotsLongPollingApplication.class);
        log.info("Telegram bot started successfully");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.toString());
        }
    }
}
