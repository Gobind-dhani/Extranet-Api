package com.rms.extranet.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Slf4j
@Component
public class RestTemplateConfig {

    @Bean
    public RestTemplate getSslEnabledRestTemplate(RestTemplateBuilder builder) {
        try {
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, (chain, authType) -> true)
                    .build();

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

            return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));

        } catch (Exception e) {
            log.error("Failed to create SSL-enabled RestTemplate", e);
            throw new RuntimeException();
        }
    }
}
