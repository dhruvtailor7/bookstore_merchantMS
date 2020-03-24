package com.coviam.team3bookstorebackend.merchantMS.service;

import com.coviam.team3bookstorebackend.merchantMS.dto.*;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;

import java.util.List;

public interface ProductMerchantService {
    void save(ProductMerchant productMerchant);

    List<ProductMerchant> findAll();

    void removeProduct(RemoveProductDTO removeProductDTO);

    List<ProductDetailsDTO> findProductMerchant(String merchant_id);

    boolean checkQuantity(QuantityUpdateDTO quantityUpdateDTO);

    void updateQuantity(QuantityUpdateDTO quantityUpdateDTO);

    List<MerchantDetailsDTO> getMerchantByProductId(String product_id);

    ProductMerchant getDefaultMerchantByProductId(String product_id);

    String getQuantity(RemoveProductDTO removeProductDTO);

    String getPrice(RemoveProductDTO removeProductDTO);
}
