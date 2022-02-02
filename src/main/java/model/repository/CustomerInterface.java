package model.repository;

import model.entity.*;

import java.util.List;

public interface CustomerInterface extends UserInterface<Customer>{
    List<Order> findShoppingCardByUserId(Integer id);
}
