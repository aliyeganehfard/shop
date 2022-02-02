package model.repository;

import model.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements UserInterface<Admin> {
    private String query = "";

    @Override
    public void save(Admin admin) {
        try {
            query ="insert into SH_Admin (user_name, password, national_code) VALUES (?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1,admin.getUserName());
            preparedStatement.setString(2,admin.getPassword());
            preparedStatement.setString(3,admin.getNationalCode());
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
            query="delete from SH_Admin where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public void update(Admin admin) {
        try {
            query="update SH_Admin set national_code=? where id=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1,admin.getNationalCode());
            preparedStatement.setInt(2,admin.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<Admin> findAll() {
        try {
            List<Admin> adminList = new ArrayList<>();
            query = "select * from SH_Admin ";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                adminList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return adminList;
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Admin findById(Integer id) {
        try {
            query="select * from SH_Admin where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            return getResultSet(resultSet);
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findById operation was failed!");
        }
        return null;
    }

    @Override
    public Admin getResultSet(ResultSet resultSet) {
        try {
            return new Admin(
                    resultSet.getInt("id"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    resultSet.getString("national_code")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin login(String userName, String password) {
        try {
            query="select * from SH_Admin where user_name=? and password=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getResultSet(resultSet);
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("login operation was failed!");
        }
        return null;
    }
}
