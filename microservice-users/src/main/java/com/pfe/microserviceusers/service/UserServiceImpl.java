package com.pfe.microserviceusers.service;


import com.pfe.microserviceusers.models.User;
import com.pfe.microserviceusers.models.embedded.Photo;
import com.pfe.microserviceusers.models.enumuration.RoleName;
import com.pfe.microserviceusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User addPhotoToUser(User user, Photo photo) {
        user.setPhoto(photo);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String encodedStringOfImage(Long id) {
        return userRepository.getEncodStringOfImage(id);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }



    @Override
    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmailIgnoreCase(username,email);
    }

    @Override
    public long countByUsername(String username) {
        return userRepository.countByUsername(username);
    }


    @Override
    public void addRoleToUser(String username, RoleName roleName) {
        User user=userRepository.findByUsernameOrEmailIgnoreCase(username,username);
        user.setRole(roleName);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }



}
