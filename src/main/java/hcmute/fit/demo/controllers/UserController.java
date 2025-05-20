package hcmute.fit.demo.controllers;

import hcmute.fit.demo.models.User;
import hcmute.fit.demo.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Tag(name = "Users", description = "User management APIs")
@Controller
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/view")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user_list"; // Tên file HTML hiển thị danh sách
    }

    @GetMapping("/Hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("UserController is working!", HttpStatus.OK);
    }

    @GetMapping("/GetAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/GetUserById/{_id}")
    public ResponseEntity<User> getUserById(@PathVariable("_id") String id) {
        ObjectId userId = new ObjectId(id);
        Optional<User> userOptional = userService.getUserById(userId);

        return userOptional
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            user.set_id(new ObjectId());
            User created = userService.createOrUpdateUser(user);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/UpdateUser/{_id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("_id") String id,
            @RequestBody User user) {
        try {
            ObjectId userId = new ObjectId(id);

            if (!userService.getUserById(userId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            user.set_id(userId);
            User updated = userService.createOrUpdateUser(user);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteUser/{_id}")
    public HttpStatus deleteUser(@PathVariable("_id") String id) {
        ObjectId userId = new ObjectId(id);

        if (userService.getUserById(userId).isPresent()) {
            userService.deleteUser(userId);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
