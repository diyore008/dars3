package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
