package hcmute.fit.demo.services;

import hcmute.fit.demo.models.CartProduct;
import hcmute.fit.demo.repositories.CartProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartProductService {
    @Autowired
    private final CartProductRepository cartproductRepository;

    public CartProductService(CartProductRepository cartproductRepository) {
        this.cartproductRepository = cartproductRepository;
    }

    public List<CartProduct> getAllCartProducts() {
        return cartproductRepository.findAll();
    }

    public Optional<CartProduct> getCartProductById(ObjectId _id) {
        return cartproductRepository.findById(_id);
    }

    public CartProduct createOrUpdateCartProduct(CartProduct cartproduct) {
        return cartproductRepository.save(cartproduct);
    }

    public void deleteCartProduct(ObjectId _id) {
    	cartproductRepository.deleteById(_id);
    }
}
