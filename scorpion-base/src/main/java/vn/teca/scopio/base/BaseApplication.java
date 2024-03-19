package vn.teca.scopio.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import vn.teca.scopio.base.handler.RestTemplateResponseErrorHandler;

import java.time.Duration;

@SpringBootApplication
@EnableDiscoveryClient
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
    @Autowired
    private RestTemplateResponseErrorHandler errorHandler;

    @Value("${rest.connect-timeout:30s}")
    private Duration connectTimeout;

    @Value("${rest.read-timeout:30s}")
    private Duration readTimeout;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .errorHandler(errorHandler)
                .setConnectTimeout(connectTimeout == null ? Duration.ofSeconds(5) : connectTimeout)
                .setReadTimeout(readTimeout == null ? Duration.ofSeconds(5) : readTimeout)
                .build();
    }
}
