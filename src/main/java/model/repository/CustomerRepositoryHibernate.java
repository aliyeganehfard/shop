package model.repository;

import model.entity.Customer;
import model.util.SingleTonConnection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CustomerRepositoryHibernate implements BaseRepository<Customer> {
    private SessionFactory sessionFactory = SingleTonConnection.getInstance();

    @Override
    public void save(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(customer);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(id);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(customer);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(customer);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {
        try (var session = sessionFactory.openSession()) {
            var criteria = session.createCriteria(Customer.class);
            List<Customer> customerList = criteria.list();
            return customerList;
        }
    }

    @Override
    public Customer findById(Integer id) {
        try(var session = sessionFactory.openSession()){
            return session.get(Customer.class,id);
        }
    }
    public Customer login(String userName, String password){
        return findAll().stream()
                .filter(customer -> customer.getUserName().equals(userName) &&
                        customer.getPassword().equals(password))
                .findFirst().get();
    }
}
