package bespokeBikes.customer;

import bespokeBikes.customer.exception.MissingCustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String>{
        List<CustomerEntity> getAllBy() throws MissingCustomerEntity;
        Optional<CustomerEntity> findById(String customerId);
}
