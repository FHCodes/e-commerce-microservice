package br.com.e_commerce.address_service.controller;

import br.com.e_commerce.address_service.dto.request.AddressRequestDTO;
import br.com.e_commerce.address_service.dto.response.AddressResponseDTO;
import br.com.e_commerce.address_service.service.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    // Injeção por construtor
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    //Retorna todos os endereços de um cliente
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AddressResponseDTO>> getAllCustomerAddresses(
            @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(
                addressService.getAllAddressesByCustomerId(customerId)
        );
    }

    //Cria um novo endereço para um cliente
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Void> registerAddress(@Valid @RequestBody AddressRequestDTO dto,
                                                @PathVariable Long customerId
    ) {
        addressService.registerAddress(dto, customerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Atualiza um endereço existente
     */
    @PutMapping("/{addressId}/customer/{customerId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long addressId,
                                                            @PathVariable Long customerId,
                                                            @Valid @RequestBody AddressRequestDTO dto
    ) {
        AddressResponseDTO updated =
                addressService.updateAddress(dto.withId(addressId), customerId);

        return ResponseEntity.ok(updated);
    }

    /**
     * Remove um endereço
     */
    @DeleteMapping("/{addressId}/customer/{customerId}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable Long addressId,
            @PathVariable Long customerId
    ) {
        addressService.deleteAddress(addressId, customerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void>  deleteAllAddress(@PathVariable @Positive Long customerId) {
        addressService.deleteAllAddress(customerId);
        return ResponseEntity.noContent().build();
    }
}



