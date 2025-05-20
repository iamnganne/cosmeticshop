package hcmute.fit.demo.dto;

import hcmute.fit.demo.models.Cart;
import hcmute.fit.demo.models.User;
import hcmute.fit.demo.models.Order_User;
import java.util.List;

public class CartWithUserAndOrderDTO extends Cart{
    private List<User> userInfo;
    private List<Order_User> orderInfo;

    // Constructors
    public CartWithUserAndOrderDTO() {}

    public CartWithUserAndOrderDTO(Cart cart, List<User> userInfo, List<Order_User> orderInfo) {
        this.userInfo = userInfo;
        this.orderInfo = orderInfo;
    }

    // Getters and Setters

    public List<User> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<User> userInfo) {
        this.userInfo = userInfo;
    }

    public List<Order_User> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<Order_User> orderInfo) {
        this.orderInfo = orderInfo;
    }
}

