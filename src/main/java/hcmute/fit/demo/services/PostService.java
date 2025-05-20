package hcmute.fit.demo.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.fit.demo.models.Post;
import hcmute.fit.demo.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> GetAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> GetPostById (ObjectId _id) {
        return postRepository.findById(_id);
    }

    public Post CreateOrUpdatePost (Post post) {
        return postRepository.save(post);
//        return postRepository.insert(post);
    }
    public void DeletePost (ObjectId _id) {
        postRepository.deleteById(_id);
    }
}