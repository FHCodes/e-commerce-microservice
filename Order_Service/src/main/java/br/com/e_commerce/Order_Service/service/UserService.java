package com.mdp.service;

import com.mdp.dto.response.CustomerResponseDTO;
import com.mdp.entity.user.Customer;
import com.mdp.entity.user.User;
import com.mdp.mapper.CustomerMapper;
import com.mdp.repository.UserRepository;
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
