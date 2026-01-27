package com.mdp.controller;

import com.mdp.dto.request.ProductRequestDTO;
import com.mdp.dto.response.ProductResponseDTO;
import com.mdp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{sellerId}/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsBySeller (@PathVariable long sellerId){
        return ResponseEntity.ok(productService.getAllProductsList(sellerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductDTOById(id));
    }

    @PostMapping("/{sellerId}/register")
    public ResponseEntity<ProductResponseDTO> registerProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO, @PathVariable Long sellerId) {
        ProductResponseDTO createdProduct = productService.registerProduct(productRequestDTO,sellerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO updateProduct = productService.updateProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
