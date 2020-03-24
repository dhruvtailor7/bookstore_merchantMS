package com.coviam.team3bookstorebackend.merchantMS.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Merchant")
@lombok.Getter
@lombok.Setter
@ToString
public class Merchant
{

    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    private String merchantId;
    private String merchantName;
    private String merchantEmail;
    private String merchantPhone;
    private String merchantAddress;
    private String pincode;
    private String merchantRating;
    private String count;

}
