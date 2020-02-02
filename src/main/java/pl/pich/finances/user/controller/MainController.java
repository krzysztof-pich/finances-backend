package pl.pich.finances.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pich.finances.user.model.User;
import pl.pich.finances.user.service.UserService;

@RequestMapping(path = "/user")
@RestController
@CrossOrigin
public class MainController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public Integer registerUser(@RequestBody User user) {
        User createdUser = this.userService.addUser(user);
        return createdUser.getId();
    };
}
