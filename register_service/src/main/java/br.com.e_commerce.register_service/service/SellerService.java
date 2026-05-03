package br.com.e_commerce.register_service.service;


import br.com.e_commerce.register_service.dto.request.SellerRequestDTO;
import br.com.e_commerce.register_service.dto.response.SellerResponseDTO;
import br.com.e_commerce.register_service.entity.Seller;
import br.com.e_commerce.register_service.exceptions.customExceptions.EntityNotFoundException;
import br.com.e_commerce.register_service.mapper.SellerMapper;
import br.com.e_commerce.register_service.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Transactional(readOnly = true)
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " Seller not found"));
    }

    @Transactional(readOnly = true)
    public SellerResponseDTO getSellerDTOById(Long id) {
        return SellerMapper.toSellerDTO(
                sellerRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(id + " Seller not found"))
        );
    }

    @Transactional
    public void registerSeller(SellerRequestDTO sellerRequestDTO) {
        Seller seller = new Seller(sellerRequestDTO);
        sellerRepository.save(seller);
    }

    @Transactional
    public void updateSeller(SellerRequestDTO sellerDTO) {
        if (sellerDTO.id() == null) {
            throw new EntityNotFoundException(sellerDTO.id() + " Seller not found");
        }

        Seller existingSeller = getSellerById(sellerDTO.id());

        if (sellerDTO.name() != null && !sellerDTO.name().isBlank()) {
            existingSeller.setName(sellerDTO.name());
        }
        if (sellerDTO.email() != null && !sellerDTO.email().isBlank()) {
            existingSeller.setEmail(sellerDTO.email());
        }
        if (sellerDTO.password() != null && !sellerDTO.password().isBlank()) {
            existingSeller.setPassword(sellerDTO.password());
        }
        if (sellerDTO.cnpj() != null && !sellerDTO.cnpj().isBlank()) {
            existingSeller.setCnpj(sellerDTO.cnpj());
        }

        sellerRepository.save(existingSeller);
    }

    @Transactional
    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}

