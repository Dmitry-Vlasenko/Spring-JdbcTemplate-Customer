package com.dvlasenko.app.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}
