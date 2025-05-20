package hcmute.fit.demo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hcmute.fit.demo.models.CartProduct;

@Repository
public interface CartProductRepository extends MongoRepository<CartProduct, ObjectId> {
}