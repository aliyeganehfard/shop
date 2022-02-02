package model.repository;

import model.entity.Product;

import java.util.List;

public interface ProductInterface extends Repository<Product> {
    List<Product> findByCategoryId(Integer id);
}
