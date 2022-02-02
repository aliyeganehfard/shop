package controller;

import model.entity.ShoppingCard;
import model.repository.ShoppingCardRepository;

public class ShoppingCardService extends ShopService<ShoppingCard, ShoppingCardRepository>{
    public ShoppingCardService() {
        super(new ShoppingCardRepository());
    }
}
