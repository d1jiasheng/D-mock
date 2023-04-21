package com.sz.mockbean.common.http;

import com.sz.mockbean.common.mockbean.MockBeanConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
public class WebClientService {

    private WebClient webClient;

    private String baseUrl;

    public boolean isAlive() {
        if (webClient == null) {
            return false;
        }
        return true; //todo 活
    }

    public WebClientService(MockBeanConfig mockBeanConfig) {
        webClient = WebClient.create(buildBaseUrl(mockBeanConfig.getAddress(), mockBeanConfig.getHost()));
    }

    private String buildBaseUrl(String address, String host) {
        if (StringUtils.isNotEmpty(baseUrl)) {
            return baseUrl;
        }
        return address + ":" + host;
    }

    public String post(String uri, Object body) {
        Mono<String> a = this.webClient.post().uri(uri).bodyValue(body).retrieve().bodyToMono(String.class).timeout(Duration.ofSeconds(4));
        return a.block();
    }

}
