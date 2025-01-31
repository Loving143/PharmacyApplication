package com.pharmacy.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerAddressRequestBody {

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    private String email;

    private String address;
}
