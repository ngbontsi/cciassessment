package com.bontsi.cci.assessment.api.service;

import com.bontsi.cci.assessment.api.model.Task;
import com.bontsi.cci.assessment.api.model.User;
import com.bontsi.cci.assessment.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public void  deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
    public User updateUser(Long userId,User userDetails){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User existingUser = user.get();
            existingUser.setFirst_name(userDetails.getFirst_name());
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setLast_name(userDetails.getLast_name());
            return userRepository.save(existingUser);
        }
        return null;

    }

    public User getUserById(Long id) {
        return  userRepository.findById(id).get();
    }
}
