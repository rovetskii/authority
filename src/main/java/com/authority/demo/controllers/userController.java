package com.authority.demo.controllers;

import com.authority.demo.exception.ServiceException;
import com.authority.demo.models.User;
import com.authority.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {

    private final UserService userService;

    @Autowired
    public userController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
   public ResponseEntity<Object> getAllUsers ()  throws ServiceException {
            List<User> listUser = userService.getAllUsers();
            if (listUser.isEmpty()) {
               throw new ServiceException("users not found", HttpStatus.NOT_FOUND);
            }
            return  new ResponseEntity<>(listUser, HttpStatus.OK);
   }

    @GetMapping("/getUser")
    public ResponseEntity<User> findUserByUserName (@RequestBody String username)  throws ServiceException {
        User user = userService.findUserByUserName(username).orElseThrow(()->new ServiceException("user not found", HttpStatus.FORBIDDEN));
        return  new ResponseEntity<User>(user, HttpStatus.OK);
    }

   @PostMapping("/saveUser")
   public ResponseEntity<User> saveUser(@RequestBody User u ) {
       return new ResponseEntity<User>(userService.saveUser(u), HttpStatus.CREATED);
   }
}
