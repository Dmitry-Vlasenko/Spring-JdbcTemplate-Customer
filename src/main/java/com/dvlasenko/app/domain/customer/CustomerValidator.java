package com.dvlasenko.app.domain.customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerValidator {
    public Map<String, String> validateData(Customer customer) {
        Map<String, String> errors = new HashMap<>();
        if (customer.getFirstName() == null)
            errors.put("First name", "has no data");
        if (customer.getLastName() == null)
            errors.put("Last name", "has no data");
        if (customer.getPhone() == null)
            errors.put("Phone number", "has no data");
        if (customer.getAddress() == null)
            errors.put("Address", "has no data");
        return errors;
    }
}
