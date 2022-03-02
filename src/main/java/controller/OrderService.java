package controller;

import model.entity.Order;
import model.repository.OrderRepository;

import java.util.List;

public class OrderService extends ShopService<Order, OrderRepository> {
    private OrderRepository orderRepository = new OrderRepository();
    public OrderService() {
        super(new OrderRepository());
    }
    public List<Order> findShoppingCardByUserId(Integer id){
       return orderRepository.findShoppingCardByUserId(id);
    }
}
