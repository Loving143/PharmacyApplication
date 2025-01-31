package com.pharmacy.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCustomerRequestBody {

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("prescription_image")
    private String prescriptionImage;

}
