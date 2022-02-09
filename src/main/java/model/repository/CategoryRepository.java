package model.repository;

import model.entity.Admin;
import model.entity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Category> {
    private String query;

    @Override
    public void save(Category category) {
        try {
            query = "insert into SH_Category (title, description, category_id) VALUES (?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, category.getCategory().getId());
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
            query = "delete from SH_Category where id = ?";
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
    public void update(Category category) {
        try {
            query = "update SH_Category set title=? where id =?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }
    @Override
    public List<Category> findAll() {
        try {
            List<Category> categoryList = new ArrayList<>();
            query = "select * from SH_Category";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return categoryList;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Category findById(Integer id) {
        try {
            query = "select * from SH_Category where id = ?";
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
    public Category getResultSet(ResultSet resultSet) {
        try {
            return new Category(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    new Category(resultSet.getInt("category_id"))
            );
        } catch (Exception e) {
            System.out.println("getResultSet operation was failed!");
        }
        return null;
    }
}
