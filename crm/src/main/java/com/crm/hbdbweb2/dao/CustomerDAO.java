package com.crm.hbdbweb2.dao;

import java.util.List;

import com.crm.hbdbweb2.model.Customer;

public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);
}
