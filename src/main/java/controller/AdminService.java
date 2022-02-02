package controller;

import model.entity.Admin;
import model.entity.Customer;
import model.repository.AdminRepository;

public class AdminService extends UserService<Admin, AdminRepository> {

    public AdminService() {
        super(new AdminRepository());
    }
}
