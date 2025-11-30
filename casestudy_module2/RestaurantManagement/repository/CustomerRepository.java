package casestudy_module2.RestaurantManagement.repository;

import casestudy_module2.RestaurantManagement.entity.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends AccountRepository<Customer>{

    public CustomerRepository(){
        super("data/customers.csv");
    }

    @Override
    protected Customer parseFromCsv(String[] data) {
        if(data.length < 9){
            return null;
        }

        Customer c = new Customer(
                Integer.parseInt(data[0]),
                data[1],
                data[2],
                data[3],
                data[4],
                Boolean.parseBoolean(data[5].trim())
        );
        c.setEmail(data[6]);
        c.setBankAccountNumber(Integer.parseInt(data[7]));
        c.setCurrentRevenue(Double.parseDouble(data[8]));
        return c;
    }

    @Override
    protected String[] toCsvArray(Customer c) {
        return new String[] {
                c.getIdUser().toString(),
                c.getUserName(),
                c.getPassword(),
                c.getPhoneNumber(),
                c.getAddress(),
                String.valueOf(c.isLocked()),
                c.getEmail() != null ? c.getEmail() : "",
                String.valueOf(c.getBankAccountNumber()),
                String.valueOf(c.getCurrentRevenue())
        };
    }

    @Override
    protected String getCsvHeader() {
        return "idUser,userName,password,phoneNumber,address,isLocked,email,bankAccountNumber,currentRevenue";
    }

}
