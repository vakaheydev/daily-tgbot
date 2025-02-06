package vaka.daily.tgbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClientConfig(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    @Bean
    public UserClient userClient() {
        return new UserRestClient(restClient);
    }

    @Bean
    public BindingTokenClient bindingTokenClient() {
        return new BindingTokenRestClient(objectMapper, restClient);
    }

    @Bean
    public TaskTypeClient taskTypeClient() {
        return new TaskTypeRestClient(restClient);
    }
}
