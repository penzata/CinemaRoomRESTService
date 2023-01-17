package cinema.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;

@Value
@ConfigurationProperties(prefix = "cinema")
@Validated
@ConstructorBinding
public class CinemaProperties {
    @Positive int totalRows;
    @Positive int totalColumns;
    @Positive int frontRows;
    @Positive int ticketPriceFront;
    @Positive int ticketPriceBack;
}