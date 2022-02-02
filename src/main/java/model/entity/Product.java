package model.entity;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Category category;
    private Integer qty;
    private Integer price;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(Integer id, Integer qty, Integer price) {
        this.id = id;
        this.qty = qty;
        this.price = price;
    }

    public Product(String name, String description,
                   Category category, Integer qty, Integer price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.qty = qty;
        this.price = price;
    }

    public Product(Integer id, String name, String description,
                   Category category, Integer qty, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.qty = qty;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category.getId() +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
