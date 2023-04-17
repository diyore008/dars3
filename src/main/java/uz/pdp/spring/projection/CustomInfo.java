package uz.pdp.spring.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.spring.entity.Info;

@Projection(types = Info.class)
public interface CustomInfo {
    Integer getId();
    String getName();
    String getInfo();
}
