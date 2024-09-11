package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Converters.ProductConverters;
import com.example.E_Commerce.Entities.Product;
import com.example.E_Commerce.Entities.Seller;
import com.example.E_Commerce.Exceptions.InvalidSeller;
import com.example.E_Commerce.Repositories.ProductRepository;
import com.example.E_Commerce.Repositories.SellerRepository;
import com.example.E_Commerce.RequestDto.ProductRequestDto;
import com.example.E_Commerce.ResponseDto.ProductResponseDto;
import com.example.E_Commerce.Services.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String addProduct(ProductRequestDto productRequestDto) throws InvalidSeller {
        Seller seller;
        try{
            seller=sellerRepository.getById(productRequestDto.getSeller().getId());
        } catch (Exception e) {
            throw new InvalidSeller("InvalidSeller");
        }

        Product product= ProductConverters.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProductList().add(product);
        sellerRepository.save(seller);
        return "PRODUCT ADDED SUCCESSFULLY";
    }

    @Override
    public ProductResponseDto getById(int id) {
        return ProductConverters.productToProductResponseDto(productRepository.findById(id).get());
    }

    @Override
    public List<ProductResponseDto> getAll() {
        List<ProductResponseDto> list=new ArrayList<>();
       for(Product product:productRepository.findAll()){
           list.add(ProductConverters.productToProductResponseDto(product));

       }
        return list;
    }

    @Override
    public List<ProductResponseDto> getAllProductsBySellerEmail(String email) {
        List<ProductResponseDto> list=new ArrayList<>();
        for(Product product:sellerRepository.getByEmail(email).getProductList()){
            list.add(ProductConverters.productToProductResponseDto(product));
        }
       return list;
    }

    @Override
    public String delProcuctIdInSellerId(int productId,int sellerId) {
        Seller seller=sellerRepository.findById(sellerId).get();
        Product product=productRepository.findById(productId).get();
        List<Product> list=seller.getProductList();
        list.remove(product);

        seller.setProductList(list);
        sellerRepository.save(seller);
        productRepository.deleteById(productId);
        return product.getProductName()+" removed from the list of "+seller.getName();
    }

    @Override
    public List<ProductResponseDto> getTopTwoPriceProducts() {
        List<Product> productList=productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList=new ArrayList<>();
        Collections.sort(productList);
        for(int i=productList.size()-1;i>=productList.size()-2;i--){
            productResponseDtoList.add(ProductConverters.productToProductResponseDto(productList.get(i)));
        }
        return productResponseDtoList;
    }
}
