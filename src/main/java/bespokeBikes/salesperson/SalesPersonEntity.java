package bespokeBikes.salesperson;

import bespokeBikes.common.Address;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class SalesPersonEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Embedded
    private Address address;
    private String phone;
    private String startDate;
    private String terminationDate;
    private String manager;
}
