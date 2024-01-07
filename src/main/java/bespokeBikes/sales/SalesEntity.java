package bespokeBikes.sales;

import bespokeBikes.customer.CustomerEntity;
import bespokeBikes.products.ProductsEntity;
import bespokeBikes.salesperson.SalesPersonEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class SalesEntity {
    @Id
    private String id;
    @ManyToOne
    private ProductsEntity product;
    @ManyToOne
    private SalesPersonEntity salesPerson;
    @ManyToOne
    private CustomerEntity customer;
    private String salesDate;
}
