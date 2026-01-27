package br.com.e_commerce.register_service.service;

import br.com.e_commerce.register_service.dto.request.CustomerRequestDTO;
import br.com.e_commerce.register_service.dto.response.CustomerResponseDTO;
import br.com.e_commerce.register_service.entity.Customer;
import br.com.e_commerce.register_service.exceptions.customExceptions.EntityNotFoundException;
import br.com.e_commerce.register_service.mapper.CustomerMapper;
import br.com.e_commerce.register_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " Custumer not found"));
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerDTOById(Long id) {
        return CustomerMapper.toCustomerDTO(customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " Custumer not found")));
    }

    @Transactional
    public void registerCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer(customerRequestDTO);
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(CustomerRequestDTO customerDTO) {
        if (customerDTO.id() == null) {
            throw new EntityNotFoundException(customerDTO.id() + " Custumer not found");
        }

        Customer existingCustomer = getCustomerById(customerDTO.id());

        if (customerDTO.name() != null && !customerDTO.name().isBlank()) {
            existingCustomer.setName(customerDTO.name());
        }
        if (customerDTO.email() != null && !customerDTO.email().isBlank()) {
            existingCustomer.setEmail(customerDTO.email());
        }
        if (customerDTO.password() != null && !customerDTO.password().isBlank()) {
            existingCustomer.setPassword(customerDTO.password());
        }
        if (customerDTO.cpf() != null && !customerDTO.cpf().isBlank()) {
            existingCustomer.setCpf(customerDTO.cpf());
        }

        customerRepository.save(existingCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
