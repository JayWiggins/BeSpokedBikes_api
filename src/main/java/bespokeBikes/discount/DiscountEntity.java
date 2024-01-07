package bespokeBikes.discount;

import bespokeBikes.products.ProductsEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class DiscountEntity {
    @Id
    private String id;
    @ManyToOne
    private ProductsEntity product;
    private String beginDate;
    private String endDate;
    private String discountPercentage;
}
