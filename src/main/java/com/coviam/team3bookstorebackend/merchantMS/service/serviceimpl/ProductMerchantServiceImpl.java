package com.coviam.team3bookstorebackend.merchantMS.service.serviceimpl;

import com.coviam.team3bookstorebackend.merchantMS.client.ProductClient;
import com.coviam.team3bookstorebackend.merchantMS.dto.*;
import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import com.coviam.team3bookstorebackend.merchantMS.repositery.ProductMerchantRepositery;
import com.coviam.team3bookstorebackend.merchantMS.service.MerchantService;
import com.coviam.team3bookstorebackend.merchantMS.service.ProductMerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductMerchantServiceImpl implements ProductMerchantService
{
    @Autowired
    ProductMerchantRepositery productMerchantRepositery;

    @Autowired
    ProductClient productClient;

    @Autowired
    MerchantService merchantService;


    @Override
    public void save(ProductMerchant productMerchant) {


        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
        productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantArrayList.stream().filter(productMerchant1 -> {
            return (productMerchant1.getProductId().equals(productMerchant.getProductId())
                    && productMerchant1.getMerchantId().equals(productMerchant.getMerchantId()));})
                .collect(Collectors.toList());

        if(productMerchantArrayList.size()==0)
        {
            System.out.println("new product added------------");
            productMerchantRepositery.save(productMerchant);
        }
        else
        {
            ProductMerchant productMerchant1=new ProductMerchant();

            BeanUtils.copyProperties(productMerchant,productMerchant1);
            productMerchant1.setProductMerchantId(productMerchantArrayList.get(0).getProductMerchantId());
            System.out.println("-----------_Updating-------"+productMerchant1);
            System.out.println("ProductMerchantId:----"+productMerchantArrayList.get(0).getProductMerchantId());
            productMerchantRepositery.save(productMerchant1);
        }

    }

    @Override
    public List<ProductMerchant> findAll() {
        return (ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
    }


    @Override
    public ProductMerchant getDefaultMerchantByProductId(String product_id)
    {

        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantArrayList.stream().filter(productMerchant ->
        productMerchant.getProductId().equals(product_id)).sorted().collect(Collectors.toList());

        return productMerchantArrayList.get(0);

    }

    @Override
    public void removeProduct(RemoveProductDTO removeProductDTO) {


        productMerchantRepositery.deleteByProductId(removeProductDTO.getProductId());

    }

    @Override
    public List<ProductDetailsDTO> findProductMerchant(String merchant_id) {
        ArrayList<ProductMerchant> productMerchantList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
        productMerchantList=(ArrayList<ProductMerchant>) productMerchantList.stream().filter(productMerchant1 -> merchant_id.equals(productMerchant1.getMerchantId()))
                //.map(productMerchant1 -> productMerchant1.getProductId() )
                .collect(Collectors.toList());


        ArrayList<ProductDetailsDTO> productDetailsDTOS=new ArrayList<ProductDetailsDTO>();
        for(ProductMerchant productMerchant:productMerchantList) {
            System.out.println(productMerchant);
            ProductDetailsDTO productDetailsDTO = productClient.getProductById(productMerchant.getProductId());
            productDetailsDTO.setQuantity(productMerchant.getQuantity());
            productDetailsDTOS.add(productDetailsDTO);
        }

        return productDetailsDTOS;



    }


    @Override
    public boolean checkQuantity(QuantityUpdateDTO quantityUpdateDTO)
    {
        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        ArrayList<ProductMerchant> arrayList=(ArrayList<ProductMerchant>)productMerchantArrayList.stream().filter(productMerchant ->
        {return (productMerchant.getProductId().equals(quantityUpdateDTO.getProductId())
                && productMerchant.getMerchantId().equals(quantityUpdateDTO.getMerchantId()));}).collect(Collectors.toList());

        if(Integer.parseInt(arrayList.get(0).getQuantity())>=Integer.parseInt(quantityUpdateDTO.getQuantity()))
            return true;
        else
            return false;
    }


    @Override
    public void updateQuantity(QuantityUpdateDTO quantityUpdateDTO)
    {

        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        ArrayList<ProductMerchant> arrayList=(ArrayList<ProductMerchant>)productMerchantArrayList.stream().filter(productMerchant ->
        {return (productMerchant.getProductId().equals(quantityUpdateDTO.getProductId())
                && productMerchant.getMerchantId().equals(quantityUpdateDTO.getMerchantId()));}).collect(Collectors.toList());


        ProductMerchant productMerchant=arrayList.get(0);
        int quantity=Integer.parseInt(productMerchant.getQuantity())-Integer.parseInt(quantityUpdateDTO.getQuantity());

        productMerchant.setQuantity(String.valueOf(quantity));
        productMerchantRepositery.save(productMerchant);


    }


    @Override
    public List<MerchantDetailsDTO> getMerchantByProductId(String product_id)
    {



        ArrayList<ComputationDTO> computationDTOArrayList=new ArrayList<>();
        ArrayList<Merchant> merchantArrayList=new ArrayList<>();
        ArrayList<MerchantDetailsDTO> merchantDetailsDTOS=new ArrayList<>();
       // List<String> merchantIdList= productMerchantService.getMerchantByProductId(product_id);

        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        List<String> merchantIdList= (ArrayList<String>)productMerchantArrayList.stream().filter(productMerchant ->
        {return (productMerchant.getProductId().equals(product_id)
        );}).map(productMerchant -> productMerchant.getMerchantId()).collect(Collectors.toList());

        for(String merchant_id:merchantIdList)
        {
            ComputationDTO computationDTO=new ComputationDTO();
            MerchantDetailsDTO merchantDetailsDTO=new MerchantDetailsDTO();
            Merchant merchant=merchantService.getMerchant(merchant_id).get();
            RemoveProductDTO removeProductDTO=new RemoveProductDTO();
            removeProductDTO.setMerchantId(merchant_id);
            removeProductDTO.setProductId(product_id);
            merchantDetailsDTO.setMerchantId(merchant.getMerchantId());
            merchantDetailsDTO.setMerchantName(merchant.getMerchantName());
            merchantDetailsDTO.setMerchantRating(merchant.getMerchantRating());

            merchantDetailsDTO.setPrice(this.getPrice(removeProductDTO));
            BeanUtils.copyProperties(merchantDetailsDTO,computationDTO);
            computationDTO.setQuantity(this.getQuantity(removeProductDTO));
            merchantDetailsDTOS.add(merchantDetailsDTO);
            double score=Double.parseDouble(computationDTO.getMerchantRating())*0.35
                    +Double.parseDouble(computationDTO.getQuantity())*0.15
                    -Double.parseDouble(computationDTO.getPrice())*0.5;

            computationDTO.setScore(String.valueOf(score));
            computationDTO.setMerchantName(merchantDetailsDTO.getMerchantName());
            computationDTOArrayList.add(computationDTO);


        }
        computationDTOArrayList=(ArrayList<ComputationDTO>)computationDTOArrayList.stream().sorted().collect(Collectors.toList());
        ArrayList<MerchantDetailsDTO> detailsDTOArrayList=new ArrayList<>();
        for (ComputationDTO computationDTO:computationDTOArrayList)
        {
            MerchantDetailsDTO merchantDetailsDTO=new MerchantDetailsDTO();
            BeanUtils.copyProperties(computationDTO,merchantDetailsDTO);
            detailsDTOArrayList.add(merchantDetailsDTO);

        }
        return detailsDTOArrayList;




    }


    @Override
    public String getQuantity(RemoveProductDTO removeProductDTO) {

        ArrayList<ProductMerchant> productMerchantArrayList = (ArrayList<ProductMerchant>) productMerchantRepositery.findAll();

        ArrayList<ProductMerchant> arrayList = (ArrayList<ProductMerchant>) productMerchantArrayList.stream().filter(productMerchant ->
        {
            return (productMerchant.getProductId().equals(removeProductDTO.getProductId())
                    && productMerchant.getMerchantId().equals(removeProductDTO.getMerchantId()));
        }).collect(Collectors.toList());


        return arrayList.get(0).getQuantity();

    }


    @Override
    public String getPrice(RemoveProductDTO removeProductDTO)
    {
        ArrayList<ProductMerchant> productMerchantArrayList =(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        ArrayList<ProductMerchant> arrayList = (ArrayList<ProductMerchant>) productMerchantArrayList.stream().
                filter(productMerchant -> { return (productMerchant.getProductId().equals(removeProductDTO.getProductId())
                    && productMerchant.getMerchantId().equals(removeProductDTO.getMerchantId()));
                    }).collect(Collectors.toList());


        return arrayList.get(0).getPrice();

    }
}
