package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Converters.ItemConverters;
import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Entities.Product;
import com.example.E_Commerce.Enums.ProductStatus;
import com.example.E_Commerce.Exceptions.ExceedQuantity;
import com.example.E_Commerce.Exceptions.InvalidCustomerId;
import com.example.E_Commerce.Exceptions.InvalidProuductId;
import com.example.E_Commerce.Repositories.CustomerRepository;
import com.example.E_Commerce.Repositories.ItemRepository;
import com.example.E_Commerce.Repositories.ProductRepository;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;
import com.example.E_Commerce.Services.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws Exception {

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new InvalidProuductId("Product doesn't exist");
        }

        Customer customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        if(customer==null){
            throw new InvalidCustomerId("Customer doesn't exist");
        }

        Product product = productOptional.get();
        if(product.getStockAvailable()==0){
            throw new Exception("Product is out of stock");
        }
        if(product.getStockAvailable()<itemRequestDto.getRequiredQuantity()){
            throw new ExceedQuantity("Sorry! The required quantity is not avaiable");
        }

        Item item = ItemConverters.ItemRequestDtoToItem(itemRequestDto);
        return item;
    }



    @Override
    public List<ItemResponseDto> getAll() {

        List<Item> itemList=itemRepository.findAll();
        List<ItemResponseDto> itemResponseDtos=new ArrayList<>();
        for(Item item:itemList){
            ItemResponseDto itemResponseDto=ItemConverters.itemResponseDtoToItem(item);
            if(!itemResponseDtos.contains(itemResponseDto)) {
                itemResponseDtos.add(itemResponseDto);
            }
        }

        return itemResponseDtos;
    }

    @Override
    public String delAllItemsInCartByCustomerId(int id) {
        Customer customer=customerRepository.findById(id).get();
        customer.getCart().getItems().clear();
        customer.getCart().setCartTotal(0);
        customer.getCart().setNumberOfItems(0);
        customerRepository.save(customer);
        return "Succesfully Removed All items in "+customer.getName()+" cart";
    }

    @Override
    public String deletAllItems() {
        itemRepository.deleteAll();
        return "Successfully removed all items.....";
    }
}
