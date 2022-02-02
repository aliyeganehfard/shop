package model.entity;

import model.repository.UserInterface;

public class Admin extends User  {
    private String nationalCode;

    public Admin() {
    }

    public Admin(String userName, String password,
                 String nationalCode) {
        super(userName, password);
        this.nationalCode = nationalCode;
    }

    public Admin(Integer id, String userName,
                 String password, String nationalCode) {
        super(id, userName, password);
        this.nationalCode = nationalCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
