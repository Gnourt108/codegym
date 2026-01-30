package com.example.displaycustomerlist.repository;

import com.example.displaycustomerlist.entity.Customer;

import java.util.List;

public interface ICustomerRepo {
    List<Customer> findAll();
    boolean add(Customer customer);
}
