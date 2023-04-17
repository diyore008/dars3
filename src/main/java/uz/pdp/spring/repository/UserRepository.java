package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.spring.entity.User;
import uz.pdp.spring.projection.CustomUser;

@RepositoryRestResource(collectionResourceRel = "list", path = "user", excerptProjection = CustomUser.class)
public interface UserRepository extends JpaRepository<User, Integer> {
}
