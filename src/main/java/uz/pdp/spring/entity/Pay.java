package uz.pdp.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pay {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private double summa;
    @ManyToOne
    private User user;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    private Basket basket;

    public void setId(Long id) {
        this.id = Math.toIntExact(id);
    }

    public Integer getId() {
        return id;
    }
}
