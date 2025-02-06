package vaka.daily.tgbot.handler.specific;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import vaka.daily.tgbot.handler.AbstractMessageHandler;
import vaka.daily.tgbot.handler.command.CommandHandler;
import vaka.daily.tgbot.handler.command.StartCommandHandler;
import vaka.daily.tgbot.service.*;

import java.util.Arrays;
import java.util.Map;

import static vaka.daily.tgbot.core.update.MessageType.COMMAND;

@Slf4j
@Component
public class CommandMessageHandler extends AbstractMessageHandler {
    private Map<String, CommandHandler> handlers;
    private MessageHandlerService messageService;
    private UserService userService;
    private TelegramBindingService telegramBindingService;

    @Autowired
    public CommandMessageHandler(TelegramMessageService telegramMessageService,
                                 MessageHandlerService messageHandlerService,
                                 UserService userService,
                                 TelegramBindingService telegramBindingService) {
        super(telegramMessageService);
        this.messageService = messageHandlerService;
        this.userService = userService;
        this.telegramBindingService = telegramBindingService;

        handlers = Map.of(
                "start", new StartCommandHandler(userService, telegramBindingService),
                "id", (msg, cmd, args) -> String.valueOf(msg.getChatId())
        );
    }

    @Override
    public void handle(Message message) {
        String command = getCommand(message);
        String[] args = getArgs(message);
        CommandHandler commandHandler = handlers.getOrDefault(command, (msg, cmd, msgArgs) -> "Неизвестная команда :3");
        String result = commandHandler.handle(message, command, args);

        sendText(message.getChatId(), result);
    }

    private String getCommand(Message msg) {
        return msg.getText().split(" ")[0].substring(1).toLowerCase();
    }

    private String[] getArgs(Message msg) {
        String[] commandSplit = msg.getText().split(" ");
        String[] args = new String[commandSplit.length - 1];
        System.arraycopy(commandSplit, 1, args, 0, commandSplit.length - 1);
        return args;
    }
}
