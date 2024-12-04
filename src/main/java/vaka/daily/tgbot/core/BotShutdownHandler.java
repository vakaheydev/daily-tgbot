package vaka.daily.tgbot.core;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@Slf4j
public class BotShutdownHandler {
    private final TelegramBotsLongPollingApplication botsApplication;

    public BotShutdownHandler(TelegramBotsLongPollingApplication botsApplication) {
        this.botsApplication = botsApplication;
    }

    @PreDestroy
    public void onShutdown() {
        try {
            botsApplication.close();
            log.info("Telegram bot stopped successfully");
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
