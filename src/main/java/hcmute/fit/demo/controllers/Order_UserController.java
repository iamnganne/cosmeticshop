package hcmute.fit.demo.controllers;

import hcmute.fit.demo.models.Order_User;
import hcmute.fit.demo.services.Order_UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Orders", description = "Order management APIs")
@Controller
@RequestMapping("/OrderController")
public class Order_UserController {

    @Autowired
    private final Order_UserService orderService;

    public Order_UserController(Order_UserService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/view")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order_list"; // Tên file HTML để hiển thị danh sách order
    }

    @GetMapping("/Hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("OrderController is working!", HttpStatus.OK);
    }

    @GetMapping("/GetAllOrders")
    public ResponseEntity<List<Order_User>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/GetOrderById/{_id}")
    public ResponseEntity<Order_User> getOrderById(@PathVariable("_id") String id) {
        ObjectId objectId = new ObjectId(id);
        Optional<Order_User> orderOptional = orderService.getOrderById(objectId);

        return orderOptional
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Create")
    public ResponseEntity<Order_User> createOrder(@RequestBody Order_User orderUser) {
        try {
            orderUser.set_id(new ObjectId());
            Order_User created = orderService.createOrUpdateOrder(orderUser);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/UpdateOrder/{_id}")
    public ResponseEntity<Order_User> updateOrder(@PathVariable("_id") String id, @RequestBody Order_User orderUser) {
        try {
            ObjectId objectId = new ObjectId(id);

            if (!orderService.getOrderById(objectId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            orderUser.set_id(objectId);
            Order_User updated = orderService.createOrUpdateOrder(orderUser);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteOrder/{_id}")
    public HttpStatus deleteOrder(@PathVariable("_id") String id) {
        ObjectId objectId = new ObjectId(id);

        if (orderService.getOrderById(objectId).isPresent()) {
            orderService.deleteOrder(objectId);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
