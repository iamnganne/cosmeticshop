package hcmute.fit.demo.repositories;

import hcmute.fit.demo.models.Order_User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_UserRepository extends MongoRepository<Order_User, ObjectId> {
}
