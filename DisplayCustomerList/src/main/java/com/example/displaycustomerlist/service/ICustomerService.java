package com.example.displaycustomerlist.service;

import com.example.displaycustomerlist.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    boolean add(Customer customer);
}
