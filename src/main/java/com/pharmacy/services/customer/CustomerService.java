package com.pharmacy.services.customer;


import com.pharmacy.dtos.request.AddCustomerAddressRequestBody;
import com.pharmacy.dtos.request.LoginCustomerRequestBody;
import com.pharmacy.dtos.request.ValidateOtpRequestBody;
import com.pharmacy.dtos.response.AddCustomerAddressResponseModel;
import com.pharmacy.dtos.response.LoginCustomerResponseModel;
import com.pharmacy.dtos.response.ValidateOtpResponseModel;

public interface CustomerService {

    LoginCustomerResponseModel addCustomerLogin(LoginCustomerRequestBody requestBody);

    ValidateOtpResponseModel validateOtp(ValidateOtpRequestBody requestBody);

    AddCustomerAddressResponseModel addCustomerAddress(AddCustomerAddressRequestBody requestBody);
}
