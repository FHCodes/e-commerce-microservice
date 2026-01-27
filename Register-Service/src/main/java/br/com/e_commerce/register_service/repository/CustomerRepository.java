package br.com.e_commerce.register_service.repository;

import br.com.e_commerce.register_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
