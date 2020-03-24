package com.coviam.team3bookstorebackend.merchantMS.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class MerchantRatingDTO
{
    private String merchantId;
    private String merchantRating;
}
