package com.coviam.team3bookstorebackend.merchantMS.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ComputationDTO implements Comparable<ComputationDTO>
{
    private String merchantId;
    private String quantity;
    private String price;
    private String merchantRating;
    private String score;
    private String merchantName;

    @Override
    public int compareTo(ComputationDTO o) {
        double thisScore=Double.parseDouble(this.score);
        double otherScore=Double.parseDouble(o.score);

        if(thisScore>otherScore)
            return -1;
        else if(thisScore<otherScore)
            return 1;
        else
            return 0;
    }
}
