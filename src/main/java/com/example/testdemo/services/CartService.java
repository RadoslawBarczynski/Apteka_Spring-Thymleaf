package com.example.testdemo.services;

import com.example.testdemo.Repositories.CartRepository;
import com.example.testdemo.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ChemicalsService chemicalsService;

    public List<CartItem> GetCartItems(){
        return cartRepository.findAll();
    }

    public void AddToCartItem(CartItem cartItem){
        cartRepository.save(cartItem);
    }

    public void DeleteCartItem(Long id){
        cartRepository.deleteById(id);
    }

}
