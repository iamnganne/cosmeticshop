package hcmute.fit.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cart")
public class Cart {
    @Id
    private ObjectId _id;

    private int CartID;
    private Integer UserID;
    private String CartCode;
    private Integer OrderID;
    private boolean Active;

    // Getters and setters
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getCartCode() {
        return CartCode;
    }

    public void setCartCode(String cartCode) {
        CartCode = cartCode;
    }

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }
}
