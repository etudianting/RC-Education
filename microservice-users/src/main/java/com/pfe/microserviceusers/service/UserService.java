package com.pfe.microserviceusers.service;


import com.pfe.microserviceusers.models.User;
import com.pfe.microserviceusers.models.embedded.Photo;
import com.pfe.microserviceusers.models.enumuration.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public User addPhotoToUser(User user, Photo photo);
    public User updateUser(User user);
    public String encodedStringOfImage(Long id);
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByUsernameOrEmail(String username, String email);
    public  long countByUsername(String name);
    public void addRoleToUser(String username, RoleName role);
    Optional<User> findById(Long id);
    Page<User> findAll(Pageable pageable);
    void deleteUser(Long id);

}