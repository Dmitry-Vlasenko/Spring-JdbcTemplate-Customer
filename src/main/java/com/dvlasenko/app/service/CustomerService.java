package com.dvlasenko.app.service;

import com.dvlasenko.app.domain.customer.Customer;
import com.dvlasenko.app.domain.customer.CustomerValidator;
import com.dvlasenko.app.exceptions.CustomerException;
import com.dvlasenko.app.network.*;
import com.dvlasenko.app.repository.impl.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("customerService")
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public String create(Customer customer) {
        Optional<Customer> optional;
        if (repository.create(customer)) {
            optional = repository.getLastEntity();
            if (optional.isPresent()) {
                Customer customerCreated = optional.get();
                return new ResponseUtil<ResponseData<Customer>>()
                        .getResponse(new ResponseData<>(HttpStatus.CREATED.toString(),
                                true, customerCreated));
            } else return new ResponseUtil<ResponseInfo>()
                    .getResponse(new ResponseInfo(HttpStatus.NOT_FOUND.toString(),
                            false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
        } else return new ResponseUtil<ResponseInfo>()
                .getResponse(new ResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
    }

    public String fetchAll() {
        Optional<List<Customer>> optional = repository.fetchAll();
        if (optional.isPresent()) {
            List<Customer> list = optional.get();
            return new ResponseUtil<ResponseDataList<Customer>>()
                    .getResponse(new ResponseDataList<>(HttpStatus.OK.toString(),
                            true, list));
        } else return new ResponseUtil<ResponseInfo>()
                .getResponse(new ResponseInfo(HttpStatus.NOT_FOUND.toString(),
                        false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
    }

    public String fetchById(Long id) {
        Optional<Customer> optional = repository.fetchById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            return new ResponseUtil<ResponseData<Customer>>()
                    .getResponse(new ResponseData<>(HttpStatus.OK.toString(),
                            true, customer));
        } else return new ResponseUtil<ResponseInfo>()
                .getResponse(new ResponseInfo(HttpStatus.NOT_FOUND.toString(),
                        false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
    }

    public String update(Long id, Customer customer) {
        Optional<Customer> optional = repository.fetchById(id);
        if (optional.isPresent()) {
            Map<String, String> errors =
                    new CustomerValidator().validateData(customer);
            if (!errors.isEmpty()) {
                try {
                    throw new CustomerException("Check inputs",
                            errors);
                } catch (CustomerException e) {
                    return new ResponseUtil<ResponseInfo>()
                            .getResponse(new ResponseInfo(HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                                    false, e.getErrors(errors)));
                }
            }
            if (repository.update(id, customer)) {
                Optional<Customer> optional1 = repository.fetchById(id);
                if (optional1.isPresent()) {
                    Customer customerUpdated = optional1.get();
                    return new ResponseUtil<ResponseData<Customer>>()
                            .getResponse(new ResponseData<>(HttpStatus.OK.toString(),
                                    true, customerUpdated));
                } else return new ResponseUtil<ResponseInfo>()
                        .getResponse(new ResponseInfo(HttpStatus.NOT_FOUND.toString(),
                                false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
            } else return new ResponseUtil<ResponseInfo>()
                    .getResponse(new ResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                            false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
        } else return new ResponseUtil<ResponseInfo>()
                .getResponse(new ResponseInfo(HttpStatus.NOT_FOUND.toString(),
                        false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
    }


    public String delete(Long id) {
        if (repository.fetchById(id).isPresent()) {
            if (repository.delete(id)) {
                return new ResponseUtil<ResponseInfo>()
                        .getResponse(new ResponseInfo(HttpStatus.OK.toString(),
                                true, ResponseMessage.DELETED.getResponseMsg()));
            } else return new ResponseUtil<ResponseInfo>()
                    .getResponse(new ResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                            false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
        } else return new ResponseUtil<ResponseInfo>()
                .getResponse(new ResponseInfo(HttpStatus.NOT_FOUND.toString(),
                        false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
    }

    public String getLastEntity() {
        Optional<Customer> optional = repository.getLastEntity();
        if (optional.isPresent()) {
            Customer customerCreated = optional.get();
            return new ResponseUtil<ResponseData<Customer>>()
                    .getResponse(new ResponseData<>(HttpStatus.OK.toString(),
                            true, customerCreated));
        } else return new ResponseUtil<ResponseInfo>()
                .getResponse(new ResponseInfo(HttpStatus.NOT_FOUND.toString(),
                        false, ResponseMessage.SMTH_WRONG.getResponseMsg()));
    }
}
