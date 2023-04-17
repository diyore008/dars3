package uz.pdp.spring.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.spring.entity.Currency;

@Projection(types = Currency.class)
public interface CustomCurrency {
    Integer getId();
    String getName();
}
