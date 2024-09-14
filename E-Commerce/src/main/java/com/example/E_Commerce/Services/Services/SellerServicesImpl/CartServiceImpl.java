package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Converters.CartConverters;
import com.example.E_Commerce.Converters.ItemConverters;
import com.example.E_Commerce.Entities.Cart;
import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Entities.Product;
import com.example.E_Commerce.Repositories.CartRepository;
import com.example.E_Commerce.Repositories.CustomerRepository;
import com.example.E_Commerce.Repositories.ProductRepository;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.CartResponseDto;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;
import com.example.E_Commerce.Services.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;
    @Override
    public List<CartResponseDto> getAll() {
        List<Cart> cartList=cartRepository.findAll();
        List<CartResponseDto> cartResponseDtoList=new ArrayList<>();
        for(Cart cart:cartList){
            CartResponseDto cartResponseDto=CartConverters.cartToCartResponseDto(cart);
            List<ItemResponseDto> itemResponseDtos=new ArrayList<>();
            for(Item item:cart.getItems()){
                ItemConverters.itemResponseDtoToItem(item);
            }
            cartResponseDto.setItemList(itemResponseDtos);
                cartResponseDtoList.add(cartResponseDto);
        }
        return cartResponseDtoList;
    }

    @Override
    public String addItemToCart(ItemRequestDto itemRequestDto) {
        Item item= ItemConverters.ItemRequestDtoToItem(itemRequestDto);
        Cart cart=item.getCart();
        cart.getItems().add(item);
        Customer customer=customerRepository.findById(itemRequestDto.getCustomerId()).get();
        customer.setCart(cart);
        customerRepository.save(customer);

        return "Item Added to Cart Successfully";
    }



    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);
        cart.setNumberOfItems(cart.getItems().size());
        Cart savedCart = cartRepository.save(cart);  // saves both cart and item
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);
        product.getItem().add(savedItem);
        //prepare response dto
        CartResponseDto cartResponseDto=CartConverters.cartToCartResponseDto(savedCart);
        List<Item> itemList =savedCart.getItems();
        List<ItemResponseDto> itemResponseDtoList=new ArrayList<>();
        for(Item item1:itemList){
            int quantity=itemRequestDto.getRequiredQuantity();
            ItemResponseDto itemResponseDto=ItemConverters.itemResponseDtoToItem(item1);
            itemResponseDto.setQuantity(quantity);
            itemResponseDtoList.add(itemResponseDto);
        }

       cartResponseDto.setItemList(itemResponseDtoList);
        return cartResponseDto;
    }

    @Override
    public CartResponseDto getCartByCustomerId(int id) {
        Cart cart=customerRepository.findById(id).get().getCart();
        List<Item> itemList=cart.getItems();
        CartResponseDto cartResponseDto=CartConverters.cartToCartResponseDto(cart);
        List<ItemResponseDto> itemResponseDtos=new ArrayList<>();
        for(Item item1:itemList){

           itemResponseDtos.add(ItemConverters.itemResponseDtoToItem(item1));
        }
cartResponseDto.setItemList(itemResponseDtos);
        return cartResponseDto;
    }


}
