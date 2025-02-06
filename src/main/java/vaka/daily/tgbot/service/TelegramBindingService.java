package vaka.daily.tgbot.service;

import com.vaka.daily_client.client.blocked.BindingTokenClient;
import com.vaka.daily_client.exception.BindingTokenNotFoundException;
import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.BindingToken;
import com.vaka.daily_client.model.User;
import lombok.Data;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
public class TelegramBindingService {
    private final BindingTokenClient bindingTokenClient;
    private final UserService userService;

    @Autowired
    public TelegramBindingService(BindingTokenClient bindingTokenClient, UserService userService) {
        this.bindingTokenClient = bindingTokenClient;
        this.userService = userService;
    }

    @Data
    public static final class TokenValidationResult {
        private TokenValidationResult(Integer userId, String value, boolean isValid) {
            this.userId = userId;
            this.value = value;
            this.isValid = isValid;
        }

        private final Integer userId;
        private final String value;
        private final boolean isValid;
        private User user;

        public static TokenValidationResult success(Integer userId, String value) {
            return new TokenValidationResult(userId, value, true);
        }

        public static TokenValidationResult failure() {
            return new TokenValidationResult(null, null, false);
        }
    }

    public TokenValidationResult validate(String tokenValue) {
        try {
            BindingToken token = bindingTokenClient.getByTokenValue(tokenValue);
            TokenValidationResult success = TokenValidationResult.success(token.getUserId(), token.getValue());
            success.setUser(userService.getUserById(token.getUserId()));
            return success;
        } catch (BindingTokenNotFoundException ex) {
            return TokenValidationResult.failure();
        }
    }
}
