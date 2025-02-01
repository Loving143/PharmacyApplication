package com.pharmacy.services.cart;

import com.pharmacy.dtos.request.AddProductToCartRequestBody;
import com.pharmacy.dtos.response.CommonResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CartService {

    CommonResponseModel addProductToCart (AddProductToCartRequestBody requestBody);

}
