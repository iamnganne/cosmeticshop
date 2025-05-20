package hcmute.fit.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "posts")
public class Post implements Serializable {

    @Id
    private ObjectId _id;
    private String title;
    private String description;
    private String body;
    private String author;
    private String category;
    private String[] tags;
    private long likes;
    private Comment[] comments;
    private Share[] shares;

    public Post(ObjectId _id, String title, String description, String body, String author,
                String category, String[] tags, long likes, Comment[] comments, Share[] shares) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.body = body;
        this.author = author;
        this.category = category;
        this.tags = tags;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }

    public String get_id() {
        return _id.toString();
    }
    
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String[] getTags() {
        return tags;
    }

    public long getLikes() {
        return likes;
    }

    public Comment[] getComments() {
        return comments;
    }

    public Share[] getShares() {
        return shares;
    }

    public static class Comment {
        private ObjectId _id;
        private String username;
        private String message;
        private long likes;

        public Comment(ObjectId _id, String username, String message, long likes) {
            this._id = _id;
            this.username = username;
            this.message = message;
            this.likes = likes;
        }

        public ObjectId get_id() {
            return _id;
        }
        
        public void set_id(ObjectId _id) {
            this._id = _id;
        }

        public String getUsername() {
            return username;
        }

        public String getMessage() {
            return message;
        }

        public long getLikes() {
            return likes;
        }
    }

    public static class Share {
        private ObjectId _id;
        private String username;

        public Share(ObjectId _id, String username) {
            this._id = _id;
            this.username = username;
        }

        public ObjectId get_id() {
            return _id;
        }
        
        public void set_id(ObjectId _id) {
            this._id = _id;
        }

        public String getUsername() {
            return username;
        }
    }

}