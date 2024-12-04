package vaka.daily.tgbot.handler.specific;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import vaka.daily.tgbot.handler.AbstractMessageHandler;
import vaka.daily.tgbot.handler.command.CommandHandler;
import vaka.daily.tgbot.service.MessageHandlerService;
import vaka.daily.tgbot.service.TelegramMessageService;

import java.util.Map;

import static vaka.daily.tgbot.core.update.MessageType.COMMAND;

@Component
public class CommandMessageHandler extends AbstractMessageHandler {
    private Map<String, CommandHandler> handlers;
    private MessageHandlerService messageService;

    public CommandMessageHandler(TelegramMessageService telegramMessageService,
                                 MessageHandlerService messageHandlerService) {
        super(telegramMessageService);
        this.messageService = messageHandlerService;

        handlers = Map.of(
                "start", (msg) -> messageHandlerService.createResponse(COMMAND, msg),
                "id", (msg) -> String.valueOf(msg.getChatId())
        );
    }

    @Override
    public void handle(Message message) {
        String command = getCommand(message.getText());
        CommandHandler commandHandler = handlers.getOrDefault(command, (msg) -> "Неизвестная команда :3");
        String result = commandHandler.handle(message);
        sendText(message.getChatId(), result);
    }

    private static String getCommand(String msg) {
        return msg.substring(1).toLowerCase();
    }
}
