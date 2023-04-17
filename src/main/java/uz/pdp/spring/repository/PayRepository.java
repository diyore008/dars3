package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring.entity.Pay;

public interface PayRepository extends JpaRepository<Pay, Integer> {
}
