package hcmute.fit.demo.services;

import hcmute.fit.demo.models.User;
import hcmute.fit.demo.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }	

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId _id) {
        return userRepository.findById(_id);
    }

    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(ObjectId _id) {
        userRepository.deleteById(_id);
    }
}
