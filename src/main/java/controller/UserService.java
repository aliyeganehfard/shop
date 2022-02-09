package controller;

import model.entity.User;
import model.repository.BaseRepository;
import model.repository.UserInterface;

public abstract class UserService<E extends User, R extends BaseRepository<E>> extends ShopService<E, R> {
    R r;

    public UserService(R r) {
        super(r);
        this.r = r;
    }

    public E login(String userName , String password){
        try{
            UserInterface<E> userInterface = (UserInterface<E>) r;
            return userInterface.login(userName,password);
        }catch (Exception e){
            System.out.println("not fount");
        }
        return null;
    }
}
