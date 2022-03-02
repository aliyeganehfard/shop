package model.repository;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.entity.ShoppingCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Repository<Order> {
    private String query;

    @Override
    public void save(Order order) {
        try {
            query = "insert into SH_Order (customer_id, product_id, shopping_card_id) VALUES (?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, order.getCustomer().getId());
            preparedStatement.setInt(2, order.getProduct().getId());
            preparedStatement.setInt(3, order.getShoppingCard().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("save operation was failed!");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            query = "delete from SH_Order where id=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public void update(Order order) {
        try {
            query = "update SH_Order set product_id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,order.getProduct().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            List<Order> orderList = new ArrayList<>();
            query = "select * from SH_Order";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                orderList.add(
                  getResultSet(resultSet)
                );
            preparedStatement.close();
            return orderList;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Order findById(Integer id) {
        try {
            query="select * from SH_Order where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return getResultSet(resultSet);
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("save operation was failed!");
        }
        return null;
    }

    @Override
    public Order getResultSet(ResultSet resultSet) {
        try {
            return new Order(
                    resultSet.getInt("id"),
                    new Customer(resultSet.getInt("customer_id")),
                    new Product(resultSet.getInt("product_id")),
                    new ShoppingCard(resultSet.getInt("shopping_card_id"))
            );
        } catch (Exception e) {
            System.out.println("save operation was failed!");
        }
        return null;
    }

    public List<Order> findShoppingCardByUserId(Integer id) {
        try {
            List<Order> orderList = new ArrayList<>();
            query = "select * from Customer c\n" +
                    "    inner join SH_Order SO on c.id = SO.customer_id\n" +
                    "    inner join SH_Product p on p.id = SO.product_id\n" +
                    "    inner join SH_ShoppingCard SSC on SSC.id = SO.shopping_card_id\n" +
                    "where c.id = ? and SSC.payed=false";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderList.add(
                        new Order(
                                resultSet.getInt(1),
                                new Customer(
                                        resultSet.getInt("customer_id"),
                                        resultSet.getString("userName"),
                                        resultSet.getString("password")
                                ),
                                new Product(
                                        resultSet.getInt("product_id"),
                                        resultSet.getString("name")
                                ),
                                new ShoppingCard(
                                        resultSet.getInt("shopping_card_id")
                                )
                        )
                );

            }
            return orderList;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println(e);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println("save operation was failed!");
        }
        return null;
    }
}
