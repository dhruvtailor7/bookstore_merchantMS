package com.coviam.team3bookstorebackend.merchantMS.client;


import com.coviam.team3bookstorebackend.merchantMS.dto.ProductDetailsDTO;
//import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product", url = "")
public interface ProductClient {


    @GetMapping("/getGenreList")
    List<String> getGenreList();



    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDetailsDTO productDetailsDTO);

    @GetMapping("/getProductByProductId/{id}")
    ProductDetailsDTO getProductById(@PathVariable("id") String id);



}
