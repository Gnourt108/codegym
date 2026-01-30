package com.example.displaycustomerlist.service;

import com.example.displaycustomerlist.entity.Customer;
import com.example.displaycustomerlist.repository.CustomerRepo;

import java.util.List;

public class CustomerService implements ICustomerService{
    CustomerRepo customerRepo = new CustomerRepo();

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public boolean add(Customer customer) {
        return customerRepo.add(customer);
    }
}
