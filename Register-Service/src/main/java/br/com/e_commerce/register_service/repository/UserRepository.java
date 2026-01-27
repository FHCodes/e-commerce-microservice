package br.com.e_commerce.register_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<br.com.e_commerce.register_service.entity.user.User, Long> {

}
