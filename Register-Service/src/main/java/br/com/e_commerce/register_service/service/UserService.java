package br.com.e_commerce.register_service.service;

import br.com.e_commerce.register_service.dto.response.CustomerResponseDTO;
import br.com.e_commerce.register_service.entity.Customer;
import br.com.e_commerce.register_service.entity.user.User;
import br.com.e_commerce.register_service.mapper.CustomerMapper;
import br.com.e_commerce.register_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
