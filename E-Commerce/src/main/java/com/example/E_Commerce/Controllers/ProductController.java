package com.example.E_Commerce.Controllers;

import com.example.E_Commerce.Exceptions.InvalidSeller;
import com.example.E_Commerce.RequestDto.ProductRequestDto;
import com.example.E_Commerce.ResponseDto.ProductResponseDto;
import com.example.E_Commerce.Services.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSeller {
        return  productService.addProduct(productRequestDto);
    }

    @GetMapping("/getById")
    public ProductResponseDto getById(@RequestParam("id")int id){
        return productService.getById(id);
    }

    @GetMapping("/getAllProducts")
    public List<ProductResponseDto> getAll(){
        return productService.getAll();
    }

    @GetMapping("/getProductsBySellerEmail")
    public List<ProductResponseDto> getAllProductsBySellerEmail(@RequestParam("email") String email){
        return productService.getAllProductsBySellerEmail(email);
    }

    @DeleteMapping("/delProductByIdFromSellerId")
    public String delProductById(@RequestParam("productId")int productId,@RequestParam("sellerId")int sellerId){
        return productService.delProcuctIdInSellerId(productId,sellerId);
    }

    @GetMapping("/gettingTopTwoPriceProducts")
    public List<ProductResponseDto> getTopTwoPriceProducts(){

        return productService.getTopTwoPriceProducts();
    }

    @GetMapping("/getProductByPriceAndCategory")
    public List<ProductResponseDto> getProductByPriceAndCategory(
            @RequestParam("/price")int price,@RequestParam("productCategory")String productCategory){
        return productService.getProductByPriceAndCategory(price,productCategory);
    }
}
