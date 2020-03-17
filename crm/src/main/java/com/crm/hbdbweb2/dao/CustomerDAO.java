package com.crm.hbdbweb2.dao;

import java.util.List;

import com.crm.hbdbweb2.dto.UsersDTO;
import com.crm.hbdbweb2.model.Customer;
import com.crm.hbdbweb2.model.Users;

public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);

    List<Users> getUsersList();

    void saveUser(Users user);

    void deleteUser(String username);

    Users getUser(String username);
}
