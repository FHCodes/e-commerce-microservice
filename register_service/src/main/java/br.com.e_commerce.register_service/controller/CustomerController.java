package br.com.e_commerce.register_service.controller;

import br.com.e_commerce.register_service.dto.response.CustomerResponseDTO;
import br.com.e_commerce.register_service.dto.request.CustomerRequestDTO;
import br.com.e_commerce.register_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("It's working");
    }

    @GetMapping("/{customeId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long customeId) {
        return ResponseEntity.ok(customerService.getCustomerDTOById(customeId));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.registerCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.updateCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
