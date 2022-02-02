package model.repository;

import model.util.PostgresConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface BaseRepository<T> {
    void save(T t);
    void delete(Integer id);
    void update(T t);
    List<T> findAll();
    T findById(Integer id);
}
