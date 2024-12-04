package vaka.daily.tgbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vaka.daily.tgbot.service.TelegramMessageService;

@Controller
@Slf4j
@RequestMapping("/api")
public class MessageController {
    TelegramMessageService service;

    public MessageController(TelegramMessageService service) {
        this.service = service;
    }

    @PostMapping("/{tgId}")
    public ResponseEntity<?> postMessageByTgId(@PathVariable("tgId") Long tgId, @RequestBody String message) {
        log.info("Sending message to {} from API request", tgId);
        service.sendText(tgId, message);

        return ResponseEntity.ok(message);
    }
}
