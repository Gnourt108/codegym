package com.example.displaycustomerlist.repository;

import com.example.displaycustomerlist.entity.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo implements ICustomerRepo{
    private static List<Customer> customerList = new ArrayList<>();

    static {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            customerList.add(new Customer(
                    "Gnourt",
                    sdf.parse("2005-10-08"),
                    "106 Le Dinh Ly",
                    "a.jpg"
            ));

            customerList.add(new Customer(
                    "Alice",
                    sdf.parse("2003-05-12"),
                    "12 Nguyen Van Linh",
                    "b.jpg"
            ));

            customerList.add(new Customer(
                    "Bob",
                    sdf.parse("2000-01-20"),
                    "99 Le Loi",
                    "c.jpg"
            ));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Customer> findAll() {
        return customerList;
    }

    @Override
    public boolean add(Customer customer) {
        customerList.add(customer);
        return true;
    }
}
