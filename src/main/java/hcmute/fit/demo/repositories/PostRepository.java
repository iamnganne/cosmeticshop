package hcmute.fit.demo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hcmute.fit.demo.models.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {
}