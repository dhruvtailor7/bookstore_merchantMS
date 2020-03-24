package com.coviam.team3bookstorebackend.merchantMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantDTO
{
    private String merchantId;
    private String merchantName;
    private String merchantEmail;
    private String merchantPhone;
    private String merchantAddress;
    private String pincode;

}
