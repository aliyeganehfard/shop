package model.repository;

import model.util.PostgresConnection;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Repository<T> extends BaseRepository<T>{
     default Connection getConnection(){
         return PostgresConnection.connection;
     }
    T getResultSet(ResultSet resultSet);
}
