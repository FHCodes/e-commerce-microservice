package br.com.e_commerce.adress_service.service;

import br.com.e_commerce.adress_service.dto.request.AddressRequestDTO;
import br.com.e_commerce.adress_service.dto.response.AddressResponseDTO;
import br.com.e_commerce.adress_service.entity.Address;
import br.com.e_commerce.adress_service.exceptions.customExceptions.EntityNotFoundException;
import br.com.e_commerce.adress_service.mapper.AddressMapper;
import br.com.e_commerce.adress_service.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Retorna todos os endereços do cliente.
     */
    @Transactional(readOnly = true)
    public List<AddressResponseDTO> getAllAddressesByCustomerId(Long customerId) {

        validateCustomerId(customerId);

        List<Address> addresses = addressRepository.findByCustomerId(customerId);
        return AddressMapper.toAddressDTOList(addresses);
    }

    /**
     * Registra um novo endereço para o cliente.
     */
    public void registerAddress(AddressRequestDTO dto, Long customerId) {

        validateCustomerId(customerId);

        List<Address> existingAddresses = addressRepository.findByCustomerId(customerId);

        boolean shouldBeCurrent = Boolean.TRUE.equals(dto.currentAddress());

        // Primeiro endereço sempre é o atual
        if (existingAddresses.isEmpty()) {
            shouldBeCurrent = true;
        }

        // Se marcado como atual, desativa os outros
        if (shouldBeCurrent) {
            addressRepository.deactivateCustomerAddresses(customerId);
        }

        Address address = new Address(dto);
        address.setCustomerId(customerId);
        address.setCurrentAddress(shouldBeCurrent);

        addressRepository.save(address);
    }

    /**
     * Atualiza um endereço existente.
     */
    public AddressResponseDTO updateAddress(AddressRequestDTO dto, Long customerId) {

        validateCustomerId(customerId);

        if (dto.id() == null) {
            throw new EntityNotFoundException("Address id must not be null");
        }

        Address existing = addressRepository.findById(dto.id())
                .orElseThrow(() ->
                        new EntityNotFoundException("Address " + dto.id() + " not found")
                );

        // 🔒 MODIFICADO: garante que o endereço pertence ao cliente
        if (!existing.getCustomerId().equals(customerId)) {
            throw new EntityNotFoundException("Address does not belong to customer");
        }

        // Se marcado como atual, desabilita os outros
        if (Boolean.TRUE.equals(dto.currentAddress())) {
            addressRepository.deactivateCustomerAddresses(customerId);
            existing.setCurrentAddress(true);
        }

        // Atualizações opcionais
        updateIfPresent(dto.street(), existing::setStreet);
        updateIfPresent(dto.city(), existing::setCity);
        updateIfPresent(dto.state(), existing::setState);
        updateIfPresent(dto.zipCode(), existing::setZipCode);

        if (dto.number() != null) {
            existing.setNumber(dto.number());
        }

        Address saved = addressRepository.save(existing);
        return AddressMapper.toAddressDTO(saved);
    }

    /**
     * Deleta um endereço pelo ID.
     */
    public void deleteAddress(Long addressId, Long customerId) {

        if (addressId == null) {
            throw new EntityNotFoundException("Address id must not be null");
        }

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Address " + addressId + " not found")
                );

        // 🔒 MODIFICADO: valida posse
        if (!address.getCustomerId().equals(customerId)) {
            throw new EntityNotFoundException("Address does not belong to customer");
        }

        addressRepository.delete(address);
    }

    // ============================
    // MÉTODOS AUXILIARES
    // ============================

    // ✅ NOVO: validações centralizadas
    private void validateCustomerId(Long customerId) {
        if (customerId == null) {
            throw new EntityNotFoundException("Customer id must not be null");
        }
    }

    // ✅ NOVO: evita repetição de código
    private void updateIfPresent(String value, java.util.function.Consumer<String> setter) {
        if (value != null && !value.isBlank()) {
            setter.accept(value);
        }
    }
}
