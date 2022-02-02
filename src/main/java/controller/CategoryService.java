package controller;

import model.entity.Category;
import model.repository.CategoryRepository;

public class CategoryService extends ShopService<Category, CategoryRepository>{
    public CategoryService() {
        super(new CategoryRepository());
    }
}
