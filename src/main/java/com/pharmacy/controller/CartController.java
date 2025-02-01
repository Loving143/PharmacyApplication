package com.pharmacy.controller;


import com.pharmacy.dtos.request.AddProductToCartRequestBody;
import com.pharmacy.dtos.response.CommonResponseModel;
import com.pharmacy.services.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addProductToCart")
    public ResponseEntity<CommonResponseModel> addProductToCart(@RequestBody AddProductToCartRequestBody requestBody){
        CommonResponseModel responseModel = cartService.addProductToCart(requestBody);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
