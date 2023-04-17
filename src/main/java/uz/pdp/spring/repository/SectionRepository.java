package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring.entity.Section;

public interface SectionRepository extends JpaRepository<Section,Integer> {
}
