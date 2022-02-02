package model.repository;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.entity.ShoppingCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements CustomerInterface {
    private String query;

    @Override
    public void save(Customer customer) {
        try {
            query = "insert into SH_Customer (user_name, password, address) VALUES (?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, customer.getUserName());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getAddress());
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
            query = "delete from SH_Customer where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            query = "update SH_Customer set address = ? where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, customer.getAddress());
            preparedStatement.setInt(2, customer.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<Customer> findAll() {
        try {
            List<Customer> customerList = new ArrayList<>();
            query = "select * from SH_Customer";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return customerList;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Customer findById(Integer id) {
        try {
            query = "select * from SH_Customer";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
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
    public Customer getResultSet(ResultSet resultSet) {
        try {
            return new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    resultSet.getString("address")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findShoppingCardByUserId(Integer id) {
        try {
            List<Order> orderList = new ArrayList<>();
            query = "select * from SH_Customer c\n" +
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
                                        resultSet.getString("user_name"),
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

    @Override
    public Customer login(String userName, String password) {
        try {
            query = "select * from SH_Customer where user_name=? and password=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getResultSet(resultSet);
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("login operation was failed!");
        }
        return null;
    }
}
