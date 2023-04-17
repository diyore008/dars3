package uz.pdp.spring.payload;

import lombok.Data;

@Data
public class PayDto {
    private double summa;
    private Integer userId;
    private Integer currencyId;
    private Integer basketId;
}
