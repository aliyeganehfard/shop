package model.entity;

public class Customer extends User{
    private String Address;

    public Customer() {
    }

    public Customer(Integer id) {
        super(id);
    }

    public Customer(String userName, String password,
                    String address) {
        super(userName, password);
        Address = address;
    }

    public Customer(Integer id, String userName, String password) {
        super(id, userName, password);
    }

    public Customer(Integer id, String userName,
                    String password, String address) {
        super(id, userName, password);
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Address='" + Address + '\'' +
                '}';
    }
}
