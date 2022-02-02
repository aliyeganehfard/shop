package model.entity;

public class Category {
    private Integer id;
    private String title ;
    private String description;
    private Category category;

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(String title, String description,
                    Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public Category(Integer id, String title,
                    String description, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category.getId() +
                '}';
    }
}
