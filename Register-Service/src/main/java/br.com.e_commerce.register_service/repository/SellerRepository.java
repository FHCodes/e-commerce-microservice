package br.com.e_commerce.register_service.repository;


import br.com.e_commerce.register_service.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {

}
