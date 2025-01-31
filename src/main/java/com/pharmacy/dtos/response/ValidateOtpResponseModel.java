package com.pharmacy.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ValidateOtpResponseModel {

    private Integer status;
    private String message;
    private Object data;

}
