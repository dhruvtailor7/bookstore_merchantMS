package com.coviam.team3bookstorebackend.merchantMS.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MerchantDetailsDTO
{
    private String merchantId;
    private String merchantName;
    private String merchantRating;
    private String price;
}
