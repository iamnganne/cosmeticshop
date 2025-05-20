package hcmute.fit.demo.controllers;

import hcmute.fit.demo.models.Cart;
import hcmute.fit.demo.services.CartService;
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

@Tag(name = "Carts", description = "Cart management APIs")
@Controller
@RequestMapping("/CartController")
public class CartController {

    @Autowired
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/view")
    public String viewCarts(Model model) {
        model.addAttribute("carts", cartService.getAllCarts());
        return "cart_list";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("CartController is working!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/GetAllCart")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/GetCartById/{_id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("_id") String id) {
        ObjectId cartId = new ObjectId(id);
        Optional<Cart> cartOptional = cartService.getCartById(cartId);

        return cartOptional
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Create")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        try {
            cart.set_id(new ObjectId());
            Cart created = cartService.createOrUpdateCart(cart);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/UpdateCart/{_id}")
    public ResponseEntity<Cart> updateCart(
            @PathVariable("_id") String id,
            @RequestBody Cart cart) {
        try {
            ObjectId cartId = new ObjectId(id);

            if (!cartService.getCartById(cartId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            cart.set_id(cartId);
            Cart updated = cartService.createOrUpdateCart(cart);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteCart/{_id}")
    public HttpStatus deleteProduct(@PathVariable("_id") String id) {
        ObjectId cartId = new ObjectId(id);

        if (cartService.getCartById(cartId).isPresent()) {
        	cartService.deleteCart(cartId);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/GetCartWithUserAndOrder")
    public ResponseEntity<?> getCartWithUserAndOrder() {
        try {
            return new ResponseEntity<>(cartService.getCartWithUserAndOrder(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi truy vấn cart với user và order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
