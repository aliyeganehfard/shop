package model.repository;

public interface UserInterface<T> extends Repository<T>{
    T login(String userName , String password);
}
