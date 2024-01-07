package bespokeBikes.discount;

import bespokeBikes.discount.exception.MissingDiscountException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends CrudRepository<DiscountEntity, String> {
    List<DiscountEntity> getAllBy() throws MissingDiscountException;
    Optional<DiscountEntity> findById(String bikeId);
}
