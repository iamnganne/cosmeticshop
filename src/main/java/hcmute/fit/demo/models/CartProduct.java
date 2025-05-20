package hcmute.fit.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cart_Product")
public class CartProduct {
    @Id
    private ObjectId _id;

    private int id;
    private Integer CartID;
    private Integer ProductID;
    private int quantity;
    private int totalPrice;

    // Getters and Setters

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCartID() {
        return CartID;
    }

    public void setCartID(Integer cartID) {
        CartID = cartID;
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

