package controller;

import model.entity.Customer;
import model.entity.Order;
import model.entity.ShoppingCard;
import model.repository.CustomerRepository;
import model.repository.CustomerRepositoryHibernate;
import model.repository.UserInterface;

import java.util.List;

public class CustomerService extends ShopService<Customer, CustomerRepository> {
    private final CustomerRepository customerRepository;
    public CustomerService() {
        super(new CustomerRepository());
        customerRepository = new CustomerRepository();
    }
    public List<Order> findShoppingCardByUserId(Integer id){
        return customerRepository.findShoppingCardByUserId(id);
    }
    public Customer login(String userName , String password){
        try{
            return customerRepository.login(userName,password);
        }catch (Exception e){
            System.out.println("not fount");
        }
        return null;
    }
}
