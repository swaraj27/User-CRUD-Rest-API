package com.crud.user.controller;

import com.crud.user.exception.ResourceNotFound;
import com.crud.user.model.User;
import com.crud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;

    /* List */
    /* Get All Users */
    @GetMapping(value = "/get")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    /* Lookup */
    /* Get User By Id */
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getUseById(@PathVariable(value = "id") Long userId) throws ResourceNotFound{
        return  userService.getUserById(userId);
    }

    /* Create */
    /* Create User */
    @PostMapping(value = "/post")
    public User createUser(@RequestBody User user) throws ResourceNotFound{
            return userService.createUser(user);
    }

    /* Update */
    /* Update  User */
    @PutMapping(value = "/update/{id}", consumes = "application/json")
    public ResponseEntity<User> updateUserById(@PathVariable(value = "id") Long userId, @RequestBody User userDetails)throws  ResourceNotFound{
        return userService.updateUserById(userId,userDetails);
    }

    /* Delete  */
    /* Delete User  By id */
    @DeleteMapping(value = "/delete/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFound{
        return  userService.deleteUser(userId);
    }


}
