package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Converters.CartConverters;
import com.example.E_Commerce.Converters.ItemConverters;
import com.example.E_Commerce.Converters.OrderedConverters;
import com.example.E_Commerce.Entities.*;
import com.example.E_Commerce.Enums.ProductStatus;
import com.example.E_Commerce.Exceptions.EmptyCartException;
import com.example.E_Commerce.Exceptions.ExceedQuantity;
import com.example.E_Commerce.Exceptions.InvalidCardException;
import com.example.E_Commerce.Exceptions.InvalidCustomerId;
import com.example.E_Commerce.Repositories.CardRepository;
import com.example.E_Commerce.Repositories.CartRepository;
import com.example.E_Commerce.Repositories.CustomerRepository;
import com.example.E_Commerce.Repositories.ProductRepository;
import com.example.E_Commerce.RequestDto.CheckoutRequestDto;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.CartResponseDto;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;
import com.example.E_Commerce.ResponseDto.OrderResponseDto;
import com.example.E_Commerce.Services.Services.CartService;
import com.example.E_Commerce.Services.Services.OrderService;
import com.example.E_Commerce.Services.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;
    @Override
    public List<CartResponseDto> getAll() {
        List<Cart> cartList=cartRepository.findAll();
        List<CartResponseDto> cartResponseDtoList=new ArrayList<>();
        for(Cart cart:cartList){
            cartResponseDtoList.add(CartConverters.cartToCartResponseDto(cart));
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

    @Override
    public OrderResponseDto checkOutCart(CheckoutRequestDto checkoutRequestDto) throws InvalidCustomerId, InvalidCardException, EmptyCartException, ExceedQuantity {
        Customer customer;
        try{
            customer=customerRepository.findById(checkoutRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new InvalidCustomerId("Please check your CustomerId!!!!");
        }
        Card card= cardRepository.findByCardNo(checkoutRequestDto.getCardNumber());
        Date date=new Date();
        if(card==null ||
           date.compareTo(card.getExpiryDate())>0 ||
            card.getCvv()!=checkoutRequestDto.getCvv() ||
                card.getCustomer()!=customer){
            throw new InvalidCardException("Please enter correct Card Details....");
        }
        Cart cart=customer.getCart();
        if(cart==null){
            throw new EmptyCartException("Customer cart is empty.please add items in cart...");
        }
        Ordered ordered= orderService.addOrder(customer,card);
        customer.getOrders().add(ordered);
       resetCart(cart);

        return OrderedConverters.OrderToOrderResponseDto(ordered);
    }

    @Override
    public Cart resetCart(Cart cart) {
        cart.setCartTotal(0);
        cart.setNumberOfItems(0);
        cart.setItems(new ArrayList<>());
     return cart;
    }

    @Override
    public CartResponseDto deleteItemsInCartIfItisOutOfStock(int id) {
        Customer customer=customerRepository.findById(id).get();
        Cart cart=customer.getCart();
        List<Item> itemList=cart.getItems();
        for(Item item:itemList){
            if(item.getProduct().getProductStatus().equals((ProductStatus.OUT_OF_STOCK))){
                itemList.remove(item);
            }
        }
        int totalcost=0;
        for(Item item:itemList){
            totalcost+=(item.getProduct().getPrice()*item.getProduct().getStockAvailable());
        }
        cart.setCartTotal(totalcost);
        cart.setNumberOfItems(itemList.size());
        customer.setCart(cart);
        customerRepository.save(customer);
        return CartConverters.cartToCartResponseDto(cart);
    }

}
