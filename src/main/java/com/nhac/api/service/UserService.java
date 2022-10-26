package com.nhac.api.service;

import com.nhac.api.entity.User;
import com.nhac.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public User findByEmail(String email){
        return userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user e-mail:" + email));
    }

    public void delete(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
    }

    public User insert (User user) {
        User newUser = new User(user.getName(), user.getEmail(), passwordEncoder().encode(user.getPassword()));
        return userRepository.insert(newUser);
    }

    public Boolean isNewUser(User user){
        return userRepository.findByEmail(user.getEmail())==null;
    }

    public User update (String id, User userDTO){
        User user = this.findById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    /*public User changePassword (String id, Password passwordDTO){
        User user = this.findById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }*/
}
