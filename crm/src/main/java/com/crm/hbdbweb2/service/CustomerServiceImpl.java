package com.crm.hbdbweb2.service;

import com.crm.hbdbweb2.dao.CustomerDAO;
import com.crm.hbdbweb2.dto.UsersDTO;
import com.crm.hbdbweb2.model.Authorities;
import com.crm.hbdbweb2.model.Customer;
import com.crm.hbdbweb2.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer theCustomer) {
        customerDAO.saveCustomer(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        return customerDAO.getCustomer(theId);
    }

    @Override
    @Transactional
    public void deleteCustomer(int theId) {
        customerDAO.deleteCustomer(theId);
    }

    @Override
    public List<Users> getUsersList() {
        return customerDAO.getUsersList();
    }

    @Override
    @Transactional
    public void saveUser(UsersDTO userDTO) {
        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setPassword("{bcrypt}" + user.getPassword());
        user.setEnabled(1);

        if (userDTO.getRole().equals("EMPLOYEE")) {
            user.addRole("ROLE_EMPLOYEE");
        } else if (userDTO.getRole().equals("MENAGER")) {
            user.addRole("ROLE_EMPLOYEE");
            user.addRole("ROLE_MENAGER");
        } else if (userDTO.getRole().equals("ADMIN")) {
            user.addRole("ROLE_EMPLOYEE");
            user.addRole("ROLE_MENAGER");
            user.addRole("ROLE_ADMIN");
        }
        customerDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        customerDAO.deleteUser(username);
    }

    @Override
    public UsersDTO getUser(String username) {
        int i = 0;
        Users user;
        user = customerDAO.getUser(username);
        UsersDTO userDTO = new UsersDTO();
        userDTO.setUsername(user.getUsername());
        for (Authorities tempRoles : user.getRoles()) {
            i += 1;
        }
        if (i == 1)
            userDTO.setRole("EMPLOYEE");
        if (i == 2)
            userDTO.setRole("MENAGER");
        if (i == 3)
            userDTO.setRole("ADMIN");


        return userDTO;
    }
}
