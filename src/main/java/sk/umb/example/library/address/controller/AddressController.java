package sk.umb.example.library.address.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sk.umb.example.library.address.service.AddressDetailDto;
import sk.umb.example.library.address.service.AddressRequestDto;
import sk.umb.example.library.address.service.AddressService;

import java.util.List;

@Controller
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/api/addresses")
    public List<AddressDetailDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PostMapping("/api/addresses")
    public Long createAddress(@RequestBody AddressRequestDto requestDto) {
        return addressService.createAddress(requestDto);
    }
}
