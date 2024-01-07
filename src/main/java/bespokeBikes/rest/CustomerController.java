package bespokeBikes.rest;

import bespokeBikes.customer.CustomerService;
import bespokeBikes.customer.exception.MissingCustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomers")
    public ResponseEntity getCustomers() throws MissingCustomerEntity {
        return ResponseEntity.ok(customerService.getCustomers());
    }
}
