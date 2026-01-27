package br.com.e_commerce.register_service.mapper;

import br.com.e_commerce.register_service.dto.response.CustomerResponseDTO;
import br.com.e_commerce.register_service.entity.Customer;
import java.util.List;


public class CustomerMapper {

    public static List<CustomerResponseDTO> toCustomerDTOList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerMapper::toCustomerDTO)
                .toList();
    }

    public static CustomerResponseDTO toCustomerDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCpf()
        );
    }
}

