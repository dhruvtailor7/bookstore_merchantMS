package com.coviam.team3bookstorebackend.merchantMS.repositery;

import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepositery extends CrudRepository<Merchant,String> {

}
