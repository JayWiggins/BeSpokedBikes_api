package bespokeBikes.products;

import bespokeBikes.products.exceptions.MissingProductException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductsEntity, String> {
    List<ProductsEntity> getAllBy() throws MissingProductException;
    Optional<ProductsEntity> findById(String bikeId);

}
