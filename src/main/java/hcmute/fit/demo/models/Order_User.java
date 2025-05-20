package hcmute.fit.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Order_User")
public class Order_User {
    @Id
    private ObjectId _id;

    private int OrderID;
    private int CartID;
    private boolean Paid;
    private int Total;
    private String Adress;
    private Date OrderDate;
    private Date ArriveDate;
    private Object cart; // Nếu bạn liên kết với đối tượng giỏ hàng

    // Getters and Setters

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public boolean isPaid() {
        return Paid;
    }

    public void setPaid(boolean paid) {
        Paid = paid;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public Date getArriveDate() {
        return ArriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        ArriveDate = arriveDate;
    }

    public Object getCart() {
        return cart;
    }

    public void setCart(Object cart) {
        this.cart = cart;
    }
}
