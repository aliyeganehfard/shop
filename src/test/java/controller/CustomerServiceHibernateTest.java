package controller;

import Exceptions.MoneyException;
import model.entity.Customer;
import model.repository.CustomerRepositoryHibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceHibernateTest {

    @Mock
    private CustomerRepositoryHibernate customerRepository ;
    private CustomerServiceHibernate customerService;


    @BeforeEach
    public void beforeEach() {
        customerService = new CustomerServiceHibernate();
    }

    @Test
    void save() {
        Customer customer =
                new Customer(null,"ali","yegane","lar");
        customerService.save(customer);

        ArgumentCaptor<Customer> customerArgumentCaptor =
                ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository)
                .save(customerArgumentCaptor.capture());
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
        customerService.findAll();
        verify(customerRepository.findAll());
    }

    @Test
    void findById() {
    }
}