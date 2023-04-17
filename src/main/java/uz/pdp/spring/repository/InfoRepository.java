package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.spring.entity.Info;
import uz.pdp.spring.projection.CustomInfo;

@RepositoryRestResource(collectionResourceRel = "list", path = "info", excerptProjection = CustomInfo.class)
public interface InfoRepository extends JpaRepository<Info, Integer> {
}
