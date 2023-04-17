package uz.pdp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.spring.entity.Currency;
import uz.pdp.spring.projection.CustomCurrency;

@RepositoryRestResource(collectionResourceRel = "list", path = "currency", excerptProjection = CustomCurrency.class)
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
