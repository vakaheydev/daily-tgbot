package vaka.daily.tgbot.config;

import com.vaka.daily_client.client.blocked.*;
import com.vaka.daily_client.config.RestClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestClient;

@Configuration
@Import(RestClientConfig.class)
public class ClientConfig {
    RestClient restClient;

    @Autowired
    public ClientConfig(RestClient restClient) {
        this.restClient = restClient;
    }

    @Bean
    public UserClient userClient() {
        return new UserRestClient(restClient);
    }
}
