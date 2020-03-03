package com.crm.hbdbweb2.dao;

import java.util.List;

import com.crm.hbdbweb2.model.Customer;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer theCustomer);

    public Customer getCustomer(int theId);

    public void deleteCustomer(int theId);
}
