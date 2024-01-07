package bespokeBikes.common;

import bespokeBikes.customer.CustomerEntity;
import bespokeBikes.products.ProductsEntity;
import bespokeBikes.salesperson.SalesPersonEntity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesComissions {
    @ManyToOne
    private ProductsEntity product;
    @ManyToOne
    private CustomerEntity customer;
    private String date;
    private String price;
    @ManyToOne
    private SalesPersonEntity salesPerson;
    private String commission;
}
