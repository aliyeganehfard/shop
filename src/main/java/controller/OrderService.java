package controller;

import model.entity.Order;
import model.repository.OrderRepository;

public class OrderService extends ShopService<Order, OrderRepository> {
    public OrderService() {
        super(new OrderRepository());
    }
}
