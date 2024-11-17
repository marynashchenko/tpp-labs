// UserService.java
package org.tables.user;

import org.connection.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean verifyAuthority(String email, String action) {
        User user = getUserByEmail(email);
        if (user == null) {
            return false;
        }
        return switch (user.getRole()) {
            case "ADMIN" -> true;
            case "USER" -> action.equals("create") || action.equals("update");
            default -> false;
        };
    }


    public String show() {
        List users =userRepository.readAll();
        String result = "";
        for(Object user: users){
            result += user.toString() + "\n";
        }
        return result;
    }
}