package com.crm.hbdbweb2.dao;

import com.crm.hbdbweb2.dto.UsersDTO;
import com.crm.hbdbweb2.model.Customer;


import com.crm.hbdbweb2.model.Users;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> getCustomers() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);
        List<Customer> customers = theQuery.getResultList();
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Customer theCustomer =
                currentSession.get(Customer.class, theId);
        return theCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);
        theQuery.executeUpdate();

    }

    @Override
    public List<Users> getUsersList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Users> theQuery =
                currentSession.createQuery("from Users order by username", Users.class);
        List<Users> usersList = theQuery.getResultList();
        return usersList;
    }

    @Override
    public void saveUser(Users user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("delete from Users where username=:username");
        query.setParameter("username", username);
        query.executeUpdate();
    }

    @Override
    public Users getUser(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users user = currentSession.get(Users.class, username);
        return user;
    }
}