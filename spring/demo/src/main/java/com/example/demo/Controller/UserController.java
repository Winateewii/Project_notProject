package com.example.demo.Controller;


import com.example.demo.Service.User.IUserService;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService ;

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody User user) {
        userService.registerUser(user.getUsername(),user.getPassword());
        return new ResponseEntity("register success", HttpStatus.OK);
    }
    @PostMapping(path = "/token")
    public ResponseEntity token(@RequestBody User user){
        try {
            userService.login(user.getUsername(),user.getPassword());
            return new ResponseEntity("login success", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }

    }
}
