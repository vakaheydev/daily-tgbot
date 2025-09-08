package vaka.daily.tgbot.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import vaka.daily.tgbot.model.Notification;
import vaka.daily.tgbot.service.TelegramMessageService;

@Service
@Slf4j
public class KafkaNotifierService {
    TelegramMessageService telegramMessageService;

    @KafkaListener(topics = "telegram", groupId = "notifier-group")
    public void listenNotifierGroup(Notification notification) {
        telegramMessageService.sendText(notification.getChanelUserId(), notification.getMessage());
        log.info("Received notification from Kafka and sent to Telegram: {}", notification);
    }
}
