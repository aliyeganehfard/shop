package model.entity;

public class Order {
    private Integer id;
    private Customer customer;
    private Product product;
    private ShoppingCard shoppingCard;

    public Order() {
    }

    public Order(Customer customer, Product product,
                 ShoppingCard shoppingCard) {
        this.customer = customer;
        this.product = product;
        this.shoppingCard = shoppingCard;
    }

    public Order(Integer id, Customer customer,
                 Product product, ShoppingCard shoppingCard) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.shoppingCard = shoppingCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCard getShoppingCard() {
        return shoppingCard;
    }

    public void setShoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer.getUserName() +
                ", product=" + product.getName() +
                ", shoppingCard=" + shoppingCard.getId() +
                '}';
    }
}
