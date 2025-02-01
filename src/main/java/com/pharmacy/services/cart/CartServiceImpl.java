package com.pharmacy.services.cart;

import com.pharmacy.dtos.request.AddProductToCartRequestBody;
import com.pharmacy.dtos.response.CommonResponseModel;
import com.pharmacy.entity.Products;
import com.pharmacy.entity.carts.CartDetails;
import com.pharmacy.entity.customer.CustomerMaster;
import com.pharmacy.repository.cart.CartRepository;
import com.pharmacy.repository.customer.CustomerMasterRepository;
import com.pharmacy.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CustomerMasterRepository customerMasterRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    @Transactional
    public CommonResponseModel addProductToCart(AddProductToCartRequestBody requestBody) {
        //all fields are mandatory from the frontend side
        Optional<CustomerMaster> customerMaster = customerMasterRepository.findById(requestBody.getCustomerId());
        if(customerMaster.isPresent()) {
            Optional<Products> product = productRepository.findById(requestBody.getProductId());
            if(product.isPresent()) {
                CartDetails cartDetails = new CartDetails();
                cartDetails.setProduct_id(product.get());
                cartDetails.setCustomerId(customerMaster.get());
                CartDetails saved = cartRepository.save(cartDetails);
                return new CommonResponseModel().setData(saved).setStatus(200).setMessage("Successfully added product to cart");
            }else{
                return new CommonResponseModel().setStatus(400).setMessage("Product not found..!").setData("This product does not exist or not active");
            }
        }else{
            return new CommonResponseModel().setStatus(400).setMessage("Customer not found").setData("please pass valid customer id");
        }
    }
}
