package uz.pdp.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    private double price;
    @ManyToOne
    private Info info;
    @OneToOne
    private Attachment attachment;
    @ManyToOne
    private Category category;
}
