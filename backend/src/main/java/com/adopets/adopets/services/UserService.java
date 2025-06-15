package com.adopets.adopets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adopets.adopets.model.User;
import com.adopets.adopets.repositories.UserRepository;
import com.adopets.adopets.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User validateCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public User create(User user) {
        user.setId(null); // Ensure the ID is null for a new entity
        user = this.userRepository.save(user);
        return user;
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with id: " + id, e);
        }
    }
}
