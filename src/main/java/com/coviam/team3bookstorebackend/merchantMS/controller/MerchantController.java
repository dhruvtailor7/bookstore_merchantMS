package com.coviam.team3bookstorebackend.merchantMS.controller;


import com.coviam.team3bookstorebackend.merchantMS.dto.*;
import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import com.coviam.team3bookstorebackend.merchantMS.service.MerchantService;
import com.coviam.team3bookstorebackend.merchantMS.service.ProductMerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class MerchantController
{



    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductMerchantService productMerchantService;

    @PostMapping(value = "/addMerchant")
    public String addMerchant(@RequestBody MerchantDTO merchantDTO)
    {

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        merchant.setCount("0");
        merchant.setMerchantRating("0");
        Merchant merchantCreated = merchantService.save(merchant);
        return merchantCreated.getMerchantId();

    }

    @PostMapping(value = "/addProductMerchant")
    public String addProductMerchant(@RequestBody ProductMerchantDTO productMerchantDTO)
    {


        ProductMerchant productMerchant=new ProductMerchant();

        BeanUtils.copyProperties(productMerchantDTO,productMerchant);
        System.out.println("COntroller---__>"+productMerchant);
        productMerchantService.save(productMerchant);
        return "success";
        //return new ResponseEntity<ProductMerchant>(productMerchantCreated,HttpStatus.CREATED);
    }

    @GetMapping(value="/getMerchantById/{Id}")
    public Merchant getMerchantById(@PathVariable("Id") String merchant_id)
   {
       Optional<Merchant> optionalCustomer=merchantService.getMerchant(merchant_id);
       Merchant merchant=optionalCustomer.get();
        return  merchant;
    }

    @GetMapping (value = "/getMerchantByProductId/{Id}")
    public List<MerchantDetailsDTO> getMerchantByProductId(@PathVariable("Id") String product_id)
    {

        return productMerchantService.getMerchantByProductId(product_id);

    }



    @GetMapping (value = "/getDefaultMerchantByProductId/{Id}")
    public ProductMerchant getDefaultMerchantByProductId(@PathVariable("Id") String product_id)
    {
        //ArrayList<Merchant> merchantArrayList=new ArrayList<>();
        return productMerchantService.getDefaultMerchantByProductId(product_id);

    }




    @PostMapping(value = "/addMerchantRating")
    public String addRating(@RequestBody MerchantRatingDTO merchantRatingDTO)
    {
        System.out.println("rating DTO:"+merchantRatingDTO);
        String merchantId=merchantRatingDTO.getMerchantId();
        String merchantRating = merchantRatingDTO.getMerchantRating();
        return merchantService.addRating(merchantRating,merchantId);

    }


    @GetMapping("/getProductMerchant")
    public List<ProductMerchant> getAllProductMerchant()
    {
        return productMerchantService.findAll();
    }

    @GetMapping("/getProductByMerchantId/{id}")
    public List<ProductDetailsDTO> getProductMerchantByMerchantId(@PathVariable("id") String merchant_id)
    {
        return productMerchantService.findProductMerchant(merchant_id);
    }


    @PostMapping("/getQuantity")
    public String getQuantity(@RequestBody RemoveProductDTO removeProductDTO)
    {
        return productMerchantService.getQuantity(removeProductDTO);
    }

    @PostMapping("/getPrice")
    public String getPrice(@RequestBody RemoveProductDTO removeProductDTO)
    {
        return productMerchantService.getPrice(removeProductDTO);
    }




    @DeleteMapping("/removeProduct")
    public String removeProduct(@RequestBody RemoveProductDTO removeProductDTO)
    {

        productMerchantService.removeProduct(removeProductDTO);
        return "success";
    }


    @PostMapping("/checkQuantity")
    public boolean checkQuantity(@RequestBody QuantityUpdateDTO quantityUpdateDTO)
    {
        return productMerchantService.checkQuantity(quantityUpdateDTO);
    }

    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestBody QuantityUpdateDTO quantityUpdateDTO)
    {
        productMerchantService.updateQuantity(quantityUpdateDTO);
        return "updateQuantity";

    }

   // @PostMapping(value = "/addProductRating")



}





//     ArrayList<ComputationDTO> computationDTOArrayList=new ArrayList<>();
//     ArrayList<Merchant> merchantArrayList=new ArrayList<>();
//     ArrayList<MerchantDetailsDTO> merchantDetailsDTOS=new ArrayList<>();
//     List<String> merchantIdList= productMerchantService.getMerchantByProductId(product_id);
//     for(String merchant_id:merchantIdList)
//     {
//         ComputationDTO computationDTO=new ComputationDTO();
//         MerchantDetailsDTO merchantDetailsDTO=new MerchantDetailsDTO();
//         Merchant merchant=merchantService.getMerchant(merchant_id).get();
//         RemoveProductDTO removeProductDTO=new RemoveProductDTO();
//         removeProductDTO.setMerchantId(merchant_id);
//         removeProductDTO.setProductId(product_id);
//         merchantDetailsDTO.setMerchantId(merchant.getMerchantId());
//         merchantDetailsDTO.setMerchantName(merchant.getMerchantName());
//         merchantDetailsDTO.setMerchantRating(merchant.getMerchantRating());
//
//         merchantDetailsDTO.setPrice(productMerchantService.getPrice(removeProductDTO));
//         BeanUtils.copyProperties(merchantDetailsDTO,computationDTO);
//         computationDTO.setQuantity(productMerchantService.getQuantity(removeProductDTO));
//         merchantDetailsDTOS.add(merchantDetailsDTO);
//         double score=Double.parseDouble(computationDTO.getMerchantRating())*0.35
//                      +Double.parseDouble(computationDTO.getQuantity())*0.15
//                      -Double.parseDouble(computationDTO.getPrice())*0.5;
//
//         computationDTO.setScore(String.valueOf(score));
//         computationDTO.setMerchantName(merchantDetailsDTO.getMerchantName());
//         computationDTOArrayList.add(computationDTO);
//
//
//     }
//     computationDTOArrayList=(ArrayList<ComputationDTO>)computationDTOArrayList.stream().sorted().collect(Collectors.toList());
//     ArrayList<MerchantDetailsDTO> detailsDTOArrayList=new ArrayList<>();
//        for (ComputationDTO computationDTO:computationDTOArrayList)
//        {
//            MerchantDetailsDTO merchantDetailsDTO=new MerchantDetailsDTO();
//            BeanUtils.copyProperties(computationDTO,merchantDetailsDTO);
//            detailsDTOArrayList.add(merchantDetailsDTO);
//
//        }
//
//
//
//     return detailsDTOArrayList;