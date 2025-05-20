package hcmute.fit.demo.services;

import hcmute.fit.demo.models.Order_User;
import hcmute.fit.demo.repositories.Order_UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Order_UserService {

    @Autowired
    private final Order_UserRepository orderUserRepository;

    public Order_UserService(Order_UserRepository orderRepository) {
        this.orderUserRepository = orderRepository;
    }

    public List<Order_User> getAllOrders() {
        return orderUserRepository.findAll();
    }

    public Optional<Order_User> getOrderById(ObjectId _id) {
        return orderUserRepository.findById(_id);
    }

    public Order_User createOrUpdateOrder(Order_User orderUser) {
        return orderUserRepository.save(orderUser);
    }

    public void deleteOrder(ObjectId _id) {
        orderUserRepository.deleteById(_id);
    }
}
