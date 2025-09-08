package vaka.daily.tgbot.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Notification {
    private String message;
    private String chanel;
    private String chanelUserId;
    private LocalDateTime timestamp;
}