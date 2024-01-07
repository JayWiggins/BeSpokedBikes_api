package bespokeBikes.customer;

import bespokeBikes.customer.exception.MissingCustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> getCustomers() throws MissingCustomerEntity {
        log.info("Getting list of customers.");
        List<CustomerEntity> customerEntities = customerRepository.getAllBy();
        return customerEntities;
    }
}
