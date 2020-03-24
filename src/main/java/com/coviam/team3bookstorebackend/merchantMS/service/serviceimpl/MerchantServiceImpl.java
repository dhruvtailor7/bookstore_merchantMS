package com.coviam.team3bookstorebackend.merchantMS.service.serviceimpl;

import com.coviam.team3bookstorebackend.merchantMS.dto.MerchantRatingDTO;
import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;
import com.coviam.team3bookstorebackend.merchantMS.repositery.MerchantRepositery;
import com.coviam.team3bookstorebackend.merchantMS.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService
{

   // public int count=0;
    @Autowired
    MerchantRepositery merchantRepositery;

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepositery.save(merchant);
    }

    @Override
    public Optional<Merchant> getMerchant(String merchant_id) {
        return merchantRepositery.findById(merchant_id);
    }

    @Override
    public String addRating(String merchantRating, String merchantId) {

        System.out.println(merchantId);
        Merchant merchant=merchantRepositery.findById(merchantId).get();
        System.out.println(merchant);
        System.out.println(merchant.getCount());
        int count=Integer.parseInt(merchant.getCount());
        count++;

        merchant.setCount(String.valueOf(count));
       // merchantRepositery.save(merchant);

        //count++;
        double averageRating=Double.parseDouble(merchant.getMerchantRating());

        double newRating = Double.parseDouble(merchantRating);

        double finalRating=(((averageRating*(count-1))+newRating)/count);

        Merchant merchantCreated = new Merchant();

        BeanUtils.copyProperties(merchant,merchantCreated);
        DecimalFormat df = new DecimalFormat("0.00");

        merchantCreated.setMerchantRating(String.valueOf(df.format(finalRating)));
        merchantRepositery.save(merchantCreated);

        return String.valueOf(finalRating);


    }
}
