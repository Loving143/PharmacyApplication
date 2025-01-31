package com.pharmacy.services.customer;

import com.pharmacy.dtos.request.AddCustomerAddressRequestBody;
import com.pharmacy.dtos.request.LoginCustomerRequestBody;
import com.pharmacy.dtos.request.ValidateOtpRequestBody;
import com.pharmacy.dtos.response.AddCustomerAddressResponseModel;
import com.pharmacy.dtos.response.LoginCustomerResponseModel;
import com.pharmacy.dtos.response.ValidateOtpResponseModel;
import com.pharmacy.entity.customer.CustomerAddress;
import com.pharmacy.entity.customer.CustomerMaster;
import com.pharmacy.entity.customer.CustomerOtp;
import com.pharmacy.repository.customer.CustomerAddressRepository;
import com.pharmacy.repository.customer.CustomerMasterRepository;
import com.pharmacy.repository.customer.CustomerOtpRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerHelperService customerHelperService;

    @Autowired
    private CustomerMasterRepository customerMasterRepository;

    @Autowired
    private CustomerOtpRepository customerOtpRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Override
    @Transactional
    public LoginCustomerResponseModel addCustomerLogin(LoginCustomerRequestBody requestBody) {

        Integer otpSent = customerHelperService.sentOtpToCustomer(requestBody.getEmail());
        if(otpSent != null) {
            clearAllOtpRecordsforThisEmailBeforeSendingNewOtp(requestBody.getEmail());
            //set data in otp db.

            //further we will add link of the image
            byte[] prescription= Base64.getDecoder().decode(requestBody.getPrescriptionImage());

            CustomerOtp otpModel = new CustomerOtp();
            otpModel.setEmail(requestBody.getEmail());
            otpModel.setMobileNumber(requestBody.getMobileNumber());
            otpModel.setPrescriptionImage(prescription);
            otpModel.setOtp(otpSent);
            //otp valid for 5 minutes
            otpModel.setExpiryTime( System.currentTimeMillis() + (5 * 60 * 1000) );
            customerOtpRepository.save(otpModel);
            return new LoginCustomerResponseModel().setMessage("Otp sent to "+requestBody.getEmail()).setStatus(200).setData("otp is valid for 5 minutes only");
        }else{
            return new LoginCustomerResponseModel().setMessage("Something went wrong please retry..!! ").setStatus(400).setData("Kindly once validate details and try again");
        }
    }

    private void clearAllOtpRecordsforThisEmailBeforeSendingNewOtp(String email) {
        customerOtpRepository.deleteAllByEmail(email);
    }

    @Override
    @Transactional
    public ValidateOtpResponseModel validateOtp(ValidateOtpRequestBody requestBody) {
        List<CustomerOtp> customerOtpList = customerOtpRepository.findByEmailAndOtp(requestBody.getEmail(), requestBody.getOtp());
        if(customerOtpList != null && !customerOtpList.isEmpty()) {
            CustomerOtp customerData = customerOtpList.get(customerOtpList.size()-1);
            if(customerData.getExpiryTime() <= System.currentTimeMillis()) {
                return new ValidateOtpResponseModel().setMessage("Oops your otp is expired..").setStatus(400).setData("Resend otp for login...");
            }
            Optional<CustomerMaster> isOldCustomer = customerMasterRepository.findByEmail(requestBody.getEmail());
            CustomerMaster customer;
            if(isOldCustomer.isPresent()) {
                CustomerMaster oldCustomer = isOldCustomer.get();
                oldCustomer.setMobileNumber(customerData.getMobileNumber()).setPrescriptionImage(customerData.getPrescriptionImage());
                customer = customerMasterRepository.save(oldCustomer);
            }else{
                 customer = saveCustomerData(customerData);
            }
            return new ValidateOtpResponseModel().setData(customer).setStatus(200).setMessage("Logged in successfully...");
        }else{
         return new ValidateOtpResponseModel().setMessage("Incorrect Otp..!").setStatus(400).setData("checkEmail and enter otp carefully..!");
        }
    }

    @Override
    @Transactional
    public AddCustomerAddressResponseModel addCustomerAddress(AddCustomerAddressRequestBody requestBody) {
        Optional<CustomerMaster> customer = customerMasterRepository.findByCustomerIdOrEmailOrMobileNumber(requestBody.getCustomerId(),requestBody.getEmail(),requestBody.getMobileNumber());
        if(customer.isPresent()) {
            CustomerAddress customerAddress = new CustomerAddress()
                    .setAddress(requestBody.getAddress()).setIsActive(true).setCustomerId(customer.get());
            CustomerAddress saved = customerAddressRepository.save(customerAddress);
            return new AddCustomerAddressResponseModel().setData(saved).setStatus(200).setMessage("Address saved successfully!!");
        }else{
         return new AddCustomerAddressResponseModel().setMessage("Customer with this detail not found..!").setStatus(400).setData(requestBody);
        }
    }

    private CustomerMaster saveCustomerData(CustomerOtp customerData) {
        CustomerMaster customerMaster = new CustomerMaster();
        customerMaster.setEmail(customerData.getEmail());
        customerMaster.setMobileNumber(customerData.getMobileNumber());
        customerMaster.setPrescriptionImage(customerData.getPrescriptionImage());
        return customerMasterRepository.save(customerMaster);
    }
}
