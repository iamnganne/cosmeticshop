package hcmute.fit.demo.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hcmute.fit.demo.models.Post;
import hcmute.fit.demo.services.PostService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Posts", description = "Posts management APIs")
@RestController
@RequestMapping("/PostController")
public class PostController {
    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //    @GetMapping("/getAllPosts")
    @RequestMapping(method = RequestMethod.GET, value = "/Hello")
    public ResponseEntity<String> Hello() {
        return new ResponseEntity<>("okkk roii", HttpStatus.OK);

    }

    //    @GetMapping("/getAllPosts")
    @RequestMapping(method = RequestMethod.GET, value = "/GetAllPosts")
    public ResponseEntity<List<Post>> GetAllPosts() {
    	System.out.println(postService.GetAllPosts());
        return new ResponseEntity<>(postService.GetAllPosts(), HttpStatus.OK);

    }

    //    @GetMapping("/GetPostById/{_id}")
    @RequestMapping(method = RequestMethod.GET, value = "/GetPostById/{_id}")
    public ResponseEntity<Post> GetPostById(@PathVariable(value = "_id") String postId) {
        ObjectId post_id = new ObjectId(postId);
        Optional<Post> postOptional = postService.GetPostById(post_id);

        if (postOptional.isPresent()){
            return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
        }
//        return postOptional.map(post -> new ResponseEntity<>(post, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        return postOptional.map(e -> ResponseEntity.ok().body(e)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        return postOptional.map(post -> new ResponseEntity<>(post, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/Create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            post.set_id(new ObjectId());
            
            if (post.getComments() != null) {
                for (Post.Comment comment : post.getComments()) {
                    if (comment.get_id() == null) {
                        comment.set_id(new ObjectId());
                    }
                }
            }
            
            if (post.getShares() != null) {
                for (Post.Share share : post.getShares()) {
                    if (share.get_id() == null) {
                        share.set_id(new ObjectId());
                    }
                }
            }
            
            Post createdPost = postService.CreateOrUpdatePost(post);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/UpdatePost/{_id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("_id") String id,
            @RequestBody Post post) {
        try {
            ObjectId postId = new ObjectId(id);
            
            if (!postService.GetPostById(postId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            post.set_id(postId);
            
            Post updatedPost = postService.CreateOrUpdatePost(post);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    //    @DeleteMapping("/DeletePost/{_id}")
    @RequestMapping(method = RequestMethod.DELETE, value = "/DeletePost/{_id}")
    public HttpStatus DeletePost(@PathVariable(value = "_id") String postId) {
        ObjectId post_id = new ObjectId(postId);

        if (postService.GetPostById(post_id).isPresent()) {
            postService.DeletePost(post_id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
