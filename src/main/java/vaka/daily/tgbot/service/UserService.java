package vaka.daily.tgbot.service;

import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.exception.ServerNotRespondingException;
import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public Optional<User> getUserByTgId(long tgId) {
        User userByTgId;

        try {
            userByTgId = userClient.getByTelegramId(tgId);
        } catch (UserNotFoundException ignored) {
            return Optional.empty();
        }

        return Optional.of(userByTgId);
    }

    public User getUserByLogin(String login) {
        return userClient.getByUniqueName(login);
    }
}
