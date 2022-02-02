package model.repository;

import model.entity.Category;
import model.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductInterface {
    private String query;

    @Override
    public void save(Product product) {
        try {
            query = "insert into SH_Product (name, description, category_id, qty, price) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCategory().getId());
            preparedStatement.setInt(4, product.getQty());
            preparedStatement.setInt(5, product.getPrice());
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
            query = "delete from SH_Product where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("save operation was failed!");
        }
    }

    @Override
    public void update(Product product) {
        try {
            query = "update SH_Product set qty =? , price =? where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, product.getQty());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            List<Product> productList = new ArrayList<>();
            query = "select * from SH_Product";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return productList;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Product findById(Integer id) {
        try {
            query = "select * from SH_Product where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return getResultSet(resultSet);
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("findById operation was failed!");
        }
        return null;
    }

    @Override
    public Product getResultSet(ResultSet resultSet) {
        try {
            return new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    new Category(resultSet.getInt("category_id")),
                    resultSet.getInt("qty"),
                    resultSet.getInt("price")
            );
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("ResultSet operation was failed!");
        }
        return null;
    }

    @Override
    public List<Product> findByCategoryId(Integer id) {
        try {
            List<Product> productList = new ArrayList<>();
            query = "select * from SH_Product\n" +
                    "    inner join SH_Category c on c.id = SH_Product.category_id\n" +
                    "where c.category_id =?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return productList;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("findByCategoryId operation was failed!");
        }
        return null;
    }
}
