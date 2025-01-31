package com.pharmacy.services.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerHelperService {

    public boolean sendEmail( String email ){
        /**
         *
         * logic to send email
         */
        return true;
    }

    public Integer sentOtpToCustomer( String email ){
        /**
         * logic to send otp to this email.
         *
         */
        return 1234;
    }
}
