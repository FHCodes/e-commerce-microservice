package com.mdp.controller;

import com.mdp.dto.request.SellerRequestDTO;
import com.mdp.dto.response.SellerResponseDTO;
import com.mdp.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    // Buscar um vendedor por ID (DTO)
    @GetMapping("/{id}")
    public ResponseEntity<SellerResponseDTO> getSellerById(@PathVariable Long id) {
        SellerResponseDTO seller = sellerService.getSellerDTOById(id);
        return ResponseEntity.ok(seller);
    }

    // Cadastrar novo vendedor
    @PostMapping("/register")
    public ResponseEntity<Void> registerSeller(@RequestBody @Valid SellerRequestDTO sellerRequestDTO) {
        sellerService.registerSeller(sellerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Atualizar vendedor existente
    @PutMapping("/update")
    public ResponseEntity<Void> updateSeller(@RequestBody @Valid SellerRequestDTO sellerRequestDTO) {
        sellerService.updateSeller(sellerRequestDTO);
        return ResponseEntity.noContent().build();
    }

    // Deletar vendedor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
