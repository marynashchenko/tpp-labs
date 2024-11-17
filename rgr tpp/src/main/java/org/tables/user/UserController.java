// UserController.java
package org.tables.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestParam("email") String email,
                           @RequestParam("password") String password
            , @RequestParam("role") String role
    ) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        return userService.saveUser(user);
    }

    @GetMapping
    public String getUsers() {
        return userService.show();
    }


    // Implement other methods for CRUD operations
}