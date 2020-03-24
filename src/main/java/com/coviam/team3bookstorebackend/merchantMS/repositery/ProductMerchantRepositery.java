package com.coviam.team3bookstorebackend.merchantMS.repositery;

import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMerchantRepositery extends CrudRepository<ProductMerchant,String>
{
    //void deleteByProductId(String productId);

    void deleteByProductId(String productId);
}
