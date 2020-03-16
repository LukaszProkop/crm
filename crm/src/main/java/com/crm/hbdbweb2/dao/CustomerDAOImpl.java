package com.crm.hbdbweb2.dao;

import com.crm.hbdbweb2.model.Customer;


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
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using the primary key
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

}