package com.crm.hbdbweb2.service;

import com.crm.hbdbweb2.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);
}
