package vaka.daily.tgbot.handler.command;

import com.vaka.daily_client.exception.ServerNotRespondingException;
import com.vaka.daily_client.model.User;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import vaka.daily.tgbot.service.TelegramBindingService;
import vaka.daily.tgbot.service.UserService;

import java.util.Optional;

public class StartCommandHandler implements CommandHandler {
    private final UserService userService;
    private final TelegramBindingService telegramBindingService;

    public StartCommandHandler(UserService userService, TelegramBindingService telegramBindingService) {
        this.userService = userService;
        this.telegramBindingService = telegramBindingService;
    }

    @Override
    public String handle(Message message, String command, String[] args) {
        if (args.length == 0) {
            return "Здравствуйте! Привязать телеграм к Вашему аккаунту можно на странице Вашего аккаунта в Vaka Daily";
        }

        String text = message.getText();
        long tgId = message.getChatId();

        StringBuilder msg = new StringBuilder();
        Optional<User> optionalUser;

        try {
            optionalUser = userService.getUserByTgId(tgId);
        } catch (ServerNotRespondingException ex) {
            return "Упс... сервер сейчас не работает :(\nПожалуйста, повторите попытку позже";
        }

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            msg.append(String.format("Я Вас уже знаю, %s :3", user.getLogin()));
        } else {
            String encrypted = args[0];
            var token = telegramBindingService.validate(encrypted);

            if (!token.isValid()) {
                return "При попытке привязки аккаунта к телеграмму произошла ошибка. Пожалуйста, повторите ещё раз";
            }

            User user = token.getUser();
            user.setTelegramId(tgId);
            userService.updateUserById(user.getId(), user);

            msg.append("Аутентификация прошла успешно!");
        }

        return msg.toString();
    }
}
