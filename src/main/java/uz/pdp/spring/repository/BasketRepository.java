package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
}
