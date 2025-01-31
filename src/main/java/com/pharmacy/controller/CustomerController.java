package com.pharmacy.controller;

import com.pharmacy.dtos.request.AddCustomerAddressRequestBody;
import com.pharmacy.dtos.request.LoginCustomerRequestBody;
import com.pharmacy.dtos.request.ValidateOtpRequestBody;
import com.pharmacy.dtos.response.AddCustomerAddressResponseModel;
import com.pharmacy.dtos.response.LoginCustomerResponseModel;
import com.pharmacy.dtos.response.ValidateOtpResponseModel;
import com.pharmacy.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customerLogin")
    public ResponseEntity<LoginCustomerResponseModel> addCustomerLogin(@RequestBody LoginCustomerRequestBody requestBody){
        //validation should be from frontend side for email to be required field.
        LoginCustomerResponseModel responseModel =customerService.addCustomerLogin(requestBody);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/validateLoginOtp")
    public ResponseEntity<ValidateOtpResponseModel> validateOtp(@RequestBody ValidateOtpRequestBody requestBody){
        ValidateOtpResponseModel responseModel = customerService.validateOtp(requestBody);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/addCustomerAddress")
    public ResponseEntity<AddCustomerAddressResponseModel> addAddress(@RequestBody AddCustomerAddressRequestBody requestBody){
        AddCustomerAddressResponseModel responseModel = customerService.addCustomerAddress(requestBody);
        return  new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
