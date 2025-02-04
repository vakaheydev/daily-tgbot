package vaka.daily.tgbot.service;

import com.vaka.daily_client.exception.ServerNotRespondingException;
import com.vaka.daily_client.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import vaka.daily.tgbot.core.update.MessageType;
import vaka.daily.tgbot.formatter.UserMessageFormatter;

import java.util.Optional;

@Service
public class MessageHandlerService {
    @Value("${mvc.hostname}")
    private String mvcHostname;

    @Value("${mvc.port}")
    private String mvcPort;
    UserService userService;
    UserMessageFormatter userMessageFormatter;

    public MessageHandlerService(UserService userService, UserMessageFormatter userMessageFormatter) {
        this.userService = userService;
        this.userMessageFormatter = userMessageFormatter;
    }

    public String createResponse(MessageType msgType, Message msg) {
        return switch (msgType) {
            case TEXT -> createTextResponse(msg);
            case COMMAND -> createTextResponse(msg);
            case STICKER -> createTextResponse(msg);
            case ANIMATION -> createTextResponse(msg);
            case PHOTO -> createTextResponse(msg);
            case UNDEFINED -> createTextResponse(msg);
        };
    }

    private String createTextResponse(Message message) {
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
            msg.append(String.format("Привет, %s!", user.getLogin()));
            msg.append(userMessageFormatter.format(user));
        } else {
            msg.append(
                    "Здравствуйте! Чтобы привязать телеграм к Вашему аккаунту, перейдите по ссылке и подтвердите личность:\n\n");
            msg.append("```http://" + mvcHostname + ":" + mvcPort + "/login/" + tgId + "```");
        }

        return msg.toString();
    }
}
