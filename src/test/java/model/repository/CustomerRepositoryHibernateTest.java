package model.repository;

import model.entity.Customer;
import model.util.SingleTonConnection;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryHibernateTest {

    private CustomerRepositoryHibernate customerRepository;

    @BeforeAll
    public static void setup() {
        SessionFactory sessionFactory = SingleTonConnection.getInstance();
    }

    @BeforeEach
    public void beforeEach() {
        customerRepository = new CustomerRepositoryHibernate();
    }

    @AfterEach
    public void afterEach() {
//        var sessionFactory = SingleTonConnection.getInstance();
//        try(var session = sessionFactory.openSession()) {
//            var transaction = session.beginTransaction();
//            session.createSQLQuery("DELETE FROM Customer").executeUpdate();
////            session.delete(Customer.class);
//            transaction.commit();
//        }
    }

    @Test
    void save() {
//        arrange
        var customer = new Customer(null, "admin", "admin", "fars");
//        act
        customerRepository.save(customer);
//        assert
        var loadCustomer = customerRepository.findById(customer.getId());
        assertEquals(customer.getId(), loadCustomer.getId());
    }

    @Test
    void delete() {
//        arrange
        var customer = new Customer(null, "admin", "admin", "fars");
//        act
        customerRepository.save(customer);
        int size = customerRepository.findAll().size();
        customerRepository.delete(customer.getId());
//        assert
        assertEquals(size, customerRepository.findAll().size());
    }

    @Test
    void update() {
//        arrange
        var customer = new Customer(null, "admin", "admin", "fars");
//        act
        customerRepository.save(customer);
        customer.setUserName("ali");
        customerRepository.update(customer);
//        assert
        assertEquals(customer.getUserName(), customerRepository.findById(customer.getId()).getUserName());
    }

    @Test
    void findAll() {
//        act
        int size = customerRepository.findAll().size();
        customerRepository.save(new Customer(null, "admin", "admin", "fars"));
        customerRepository.save(new Customer(null, "admin1", "admin1", "fars"));
        customerRepository.save(new Customer(null, "admin2", "admin2", "fars"));
//      assert
        assertEquals(size + 3, customerRepository.findAll().size());
    }

    @Test
    void findById() {
//        arrange
        var customer = new Customer(null, "admin", "admin", "fars");
//        act
        customerRepository.save(customer);
        var thenCustomer = customerRepository.findById(customer.getId());
//        assert
        assertAll(
                () -> assertEquals(customer.getId(), thenCustomer.getId()),
                () -> assertEquals(customer.getUserName(), thenCustomer.getUserName())
        );
    }

    @Test
    void login() {
//        arrange
        var customer = new Customer(null, "admin9", "admin9", "fars");
//        act
        customerRepository.save(customer);
        var thenCustomer = customerRepository.login("admin9","admin9");
//        assert
        assertAll(
                () -> assertEquals(customer.getId(),thenCustomer.getId()),
                () -> assertEquals(customer.getUserName(),thenCustomer.getUserName())
        );
    }
}