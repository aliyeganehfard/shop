package controller;

import model.entity.Customer;
import model.entity.Order;
import model.entity.ShoppingCard;
import model.repository.CustomerRepository;

import java.util.List;

public class CustomerService extends UserService<Customer, CustomerRepository> {
    private final CustomerRepository customerRepository;
    public CustomerService() {
        super(new CustomerRepository());
        customerRepository = new CustomerRepository();
    }
    public List<Order> findShoppingCardByUserId(Integer id){
        return customerRepository.findShoppingCardByUserId(id);
    }
}
