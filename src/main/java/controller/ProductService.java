package controller;

import model.entity.Product;
import model.repository.ProductRepository;

import java.util.List;

public class ProductService extends ShopService<Product, ProductRepository> {
    private final ProductRepository productRepository ;
    public ProductService() {
        super(new ProductRepository());
        this.productRepository = new ProductRepository();
    }

    public void findByCategoryId(Integer categoryId){
        List<Product> productList = productRepository.findByCategoryId(categoryId);
        for (Product p : productList) {
            System.out.println(p.toString());
        }
    }

}
