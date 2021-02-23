package com.lambdaschool.shoppingcart.controllers;

import com.lambdaschool.shoppingcart.repository.UserRepository;
import org.springframework.security.core.Authentication;
import com.lambdaschool.shoppingcart.models.CartItem;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.services.CartItemService;
import com.lambdaschool.shoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController
{
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/",
        produces = {"application/json"})
    public ResponseEntity<?> listCartItemsByAuthenticatedUser(Authentication authentication)
    {
        User u = userRepository.findByUsername(authentication.getName());
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

    @PutMapping(value = "/add/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> addToCart(
        Authentication authentication,
        @PathVariable
            long productid)
    {
        User u = userRepository.findByUsername(authentication.getName());
        CartItem addCartTtem = cartItemService.addToCart(u.getUserid(),
            productid,
            "I am not working");
        return new ResponseEntity<>(addCartTtem,
            HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> removeFromCart(
        Authentication authentication,
        @PathVariable
            long productid)
    {
        User u = userRepository.findByUsername(authentication.getName());
        CartItem removeCartItem = cartItemService.removeFromCart(u.getUserid(),
            productid,
            "I am still not working");
        return new ResponseEntity<>(removeCartItem,
            HttpStatus.OK);
    }
}
