package bespokeBikes.customer;

import bespokeBikes.common.Address;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class CustomerEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Embedded
    private Address address;
    private String phoneNumber;
    private String startDate;
}
