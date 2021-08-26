package com.crud.user.service;

import com.crud.user.exception.ResourceNotFound;
import com.crud.user.model.User;
import com.crud.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    /* List */
    /* Get All Users */
    public List<User> getAllUsers(){
        List<User> users =  userRepository.findAll();
        return  users;
    }


    /* Lookup */
    /* Get User By Id */
    public ResponseEntity<User> getUserById(Long userId) throws ResourceNotFound {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFound("User not found By Id "+ userId));
        return ResponseEntity.ok().body(user);
    }

    /* Create */
    /* Create User */
    public User createUser(@RequestBody User user) throws ResourceNotFound{
        return userRepository.save(user);
    }

  /* Update */
  /* Update  User */
    public  ResponseEntity<User> updateUserById(Long userId, User userDetails) throws ResourceNotFound{
      User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User Not Found By This Id "+ userId));
      System.out.println("User Id "+ userId);
      user.setName(userDetails.getName());
      user.setEmail(userDetails.getEmail());
      user.setPassword(userDetails.getPassword());
      user.setAddress(userDetails.getAddress());


      final User updatedUser = userRepository.save(user);
      return ResponseEntity.ok(updatedUser);
  }

    /* Delete  */
    /* Delete User  By id */
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFound{
        User deleteUser =  userRepository.findById(userId).orElseThrow(() ->new ResourceNotFound("User not found by Id "+ userId));
        userRepository.delete(deleteUser);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return  response;
    }


}
