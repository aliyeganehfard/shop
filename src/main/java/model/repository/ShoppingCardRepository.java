package model.repository;

import model.entity.Category;
import model.entity.Product;
import model.entity.ShoppingCard;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCardRepository implements Repository<ShoppingCard> {
    private String query;

    @Override
    public void save(ShoppingCard shoppingCard) {
        try {
            query = "insert into SH_ShoppingCard (date, payed) VALUES (?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setDate(1,shoppingCard.getDate());
            preparedStatement.setBoolean(2,shoppingCard.isPayed());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
            e.getStackTrace();
            System.out.println("save operation was failed!");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            query = "delete from SH_ShoppingCard where id=?";
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
    public void update(ShoppingCard shoppingCard) {
        try {
            query="update SH_ShoppingCard set payed = ? where id=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setBoolean(1,shoppingCard.isPayed());
            preparedStatement.setInt(2,shoppingCard.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<ShoppingCard> findAll() {
        try {
            List<ShoppingCard> shoppingCardList = new ArrayList<>();
            query ="select * from SH_ShoppingCard";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                shoppingCardList.add(
                  getResultSet(resultSet)
                );
            preparedStatement.close();
            return shoppingCardList;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public ShoppingCard findById(Integer id) {
        try {
            query = "select * from SH_ShoppingCard where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
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
    public ShoppingCard getResultSet(ResultSet resultSet) {
        try {
            return new ShoppingCard(
                    resultSet.getInt("id"),
                    resultSet.getDate("date"),
                    resultSet.getBoolean("payed")
            );
        } catch (Exception e) {
            System.out.println("resultSet operation was failed!");
        }
        return null;
    }
}
