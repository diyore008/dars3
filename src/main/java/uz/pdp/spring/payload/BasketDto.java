package uz.pdp.spring.payload;

import lombok.Data;

@Data
public class BasketDto {
    private String name;
    private Integer userId;
    private Integer productId;
}
