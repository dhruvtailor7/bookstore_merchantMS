package com.coviam.team3bookstorebackend.merchantMS.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantityUpdateDTO
{
    private String merchantId;
    private String productId;
    private String quantity;
}
