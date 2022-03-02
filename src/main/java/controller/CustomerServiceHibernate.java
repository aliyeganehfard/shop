package controller;

import model.entity.Customer;
import model.repository.BaseRepository;
import model.repository.CustomerRepositoryHibernate;

import java.util.List;


public class CustomerServiceHibernate implements BaseRepository<Customer> {
    private CustomerRepositoryHibernate crh ;
    public CustomerServiceHibernate() {
        crh = new CustomerRepositoryHibernate();
    }

    @Override
    public void save(Customer customer) {
        crh.save(customer);
    }

    @Override
    public void delete(Integer id) {
        crh.delete(id);
    }

    @Override
    public void update(Customer customer) {
        crh.update(customer);
    }

    @Override
    public List<Customer> findAll() {
        return crh.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return crh.findById(id);
    }

    public Customer login(String userName, String password){
        return crh.login(userName,password);
    }
}
