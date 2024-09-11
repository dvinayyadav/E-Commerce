package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Converters.SellerConverter;
import com.example.E_Commerce.Entities.Seller;
import com.example.E_Commerce.Exceptions.InvalidEmail;
import com.example.E_Commerce.Exceptions.InvalidPanNo;
import com.example.E_Commerce.Repositories.SellerRepository;
import com.example.E_Commerce.RequestDto.SellerRequestDto;
import com.example.E_Commerce.ResponseDto.SellerResponseDto;
import com.example.E_Commerce.Services.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.example.E_Commerce.Converters.SellerConverter.sellerRequestDtoToSeller;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public String addSeller(SellerRequestDto sellerRequestDto) {

        sellerRepository.save(sellerRequestDtoToSeller(sellerRequestDto));
        return "SELLER ADDED SUCCESSFULLY";
    }

    @Override
    public List<SellerResponseDto> getAll() {
        List<Seller> sellerList=sellerRepository.findAll();
        List<SellerResponseDto> responseDtoList=new ArrayList<>();
        for(Seller list:sellerList){
            responseDtoList.add(SellerConverter.sellerToSellerResponseDto(list));
        }
        return responseDtoList;
    }


    public SellerResponseDto getByPan(String panNo) throws InvalidPanNo {
        SellerResponseDto sellerResponseDto;
        try{
            return SellerConverter.sellerToSellerResponseDto(sellerRepository.getByPanNo(panNo));
        } catch (Exception e) {
            throw new InvalidPanNo("The PanNo is not in the Seller List");
        }

    }

    public SellerResponseDto getByEmail(String email) throws InvalidEmail {
        SellerResponseDto sellerResponseDto;
        try{
            return SellerConverter.sellerToSellerResponseDto(sellerRepository.getByEmail(email));
        } catch (Exception e) {
            throw new InvalidEmail("The Email is not in the Seller List");
        }

    }

    @Override
    public String deleteSellerById(int id) {
        sellerRepository.deleteById(id);
        return "SELLER DELETED SUCCESSFULLY ";
    }

    @Override
    public String updateMobileNoByEmail(String email, String mobileNo) {
        Seller seller=sellerRepository.getByEmail(email);


                seller.setMobileNo(mobileNo);
                sellerRepository.save(seller);
                return "MOBILE NUMBER UPDATED SUCCESSFULLY";


    }

    @Override
    public String deleteSellerByEmail(String email) {
        sellerRepository.deleteById(sellerRepository.getByEmail(email).getId());
        return "Seller Deleted Successfully";
    }

    @Override
    public SellerResponseDto getById(int id) {
        return SellerConverter.sellerToSellerResponseDto(sellerRepository.findById(id).get());
    }


}
