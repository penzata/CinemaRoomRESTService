package cinema.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationPropertiesScan
@PropertySource("classpath:cinema.properties")
@Configuration
public class AppConfig {
}