package hcmute.fit.demo.services;

import hcmute.fit.demo.models.Cart;
import hcmute.fit.demo.dto.CartWithUserAndOrderDTO;
import hcmute.fit.demo.repositories.CartRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    private final MongoTemplate mongoTemplate;

    public CartService(CartRepository cartRepository, MongoTemplate mongoTemplate) {
        this.cartRepository = cartRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(ObjectId _id) {
        return cartRepository.findById(_id);
    }

    public Cart createOrUpdateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(ObjectId _id) {
    	cartRepository.deleteById(_id);
    }
    
    public List<CartWithUserAndOrderDTO> getCartWithUserAndOrder() {
    	LookupOperation lookupUser = LookupOperation.newLookup()
    			.from("Users")
    			.localField("UserID")
    			.foreignField("UserID")
    			.as("userInfo");
    	LookupOperation lookupOrder = LookupOperation.newLookup()
    		.from("Order_User")
    		.localField("OrderID")
    		.foreignField("OrderID")
    		.as("orderInfo");
    	Aggregation aggregation = Aggregation.newAggregation(lookupUser, lookupOrder);
    	
    	return mongoTemplate.aggregate(aggregation,"Cart", CartWithUserAndOrderDTO.class).getMappedResults();
    }
}
