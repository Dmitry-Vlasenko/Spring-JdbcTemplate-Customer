package com.dvlasenko.app.domain.customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerValidator {
    public Map<String, String> validateData(Customer user) {
        Map<String, String> errors = new HashMap<>();
        if (user.getFirstName() == null)
            errors.put("First name", "has no data");
        if (user.getLastName() == null)
            errors.put("Last name", "has no data");
        if (user.getPhone() == null)
            errors.put("Phone number", "has no data");
        if (user.getAddress() == null)
            errors.put("Address", "has no data");
        return errors;
    }
}
