package com.mdp.mapper;

import com.mdp.dto.response.CustomerResponseDTO;
import com.mdp.entity.user.Customer;
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

