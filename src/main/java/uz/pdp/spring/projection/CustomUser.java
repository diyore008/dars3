package uz.pdp.spring.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.spring.entity.User;
import uz.pdp.spring.entity.User;

@Projection(types = User.class)
public interface CustomUser {
    Integer getId();
    String getUsername();
    String getPhoneNumber();
}
