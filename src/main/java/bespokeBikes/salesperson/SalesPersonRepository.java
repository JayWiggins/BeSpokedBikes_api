package bespokeBikes.salesperson;

import bespokeBikes.salesperson.exception.MissingSalesPersonException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SalesPersonRepository extends CrudRepository<SalesPersonEntity, String> {
    List<SalesPersonEntity> getAllBy() throws MissingSalesPersonException;
    Optional<SalesPersonEntity> findById(String salesPersonId);
}
