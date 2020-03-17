package com.crm.hbdbweb2.service;

import com.crm.hbdbweb2.dto.UsersDTO;
import com.crm.hbdbweb2.model.Customer;
import com.crm.hbdbweb2.model.Users;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);

    List<Users> getUsersList();

    void saveUser(UsersDTO userDTO);

    void deleteUser(String username);

    UsersDTO getUser(String username);
}
