package com.dvlasenko.app.controller;

import com.dvlasenko.app.domain.customer.Customer;
import com.dvlasenko.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("customers")
    public String createUser(@RequestBody Customer user) {
        return customerService.create(user);
    }

    @GetMapping("customers/last-entity")
    public String getLastEntity() {
        return customerService.getLastEntity();
    }

    @GetMapping("customers")
    public String fetchAllUsers() {
        return customerService.fetchAll();
    }

    @GetMapping("customers/{id}")
    public String fetchUserById(@PathVariable("id") Long id) {
        return customerService.fetchById(id);
    }

    @PutMapping("customers/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody Customer user) {
        return customerService.update(id, user);
    }

    @DeleteMapping("customers/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return customerService.delete(id);
    }
}
