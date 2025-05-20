package hcmute.fit.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {
    @Id
    private ObjectId _id;

    private int ProductID;
    private String ProductName;
    private int TypeID;
    private int SupplierID;
    private int SkinID;
    private int Price;
    private int Amount;
    private String Description;
    private String Images;
    private boolean Status;
    private Object CartID;

    // Getters and Setters

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public int getSkinID() {
        return SkinID;
    }

    public void setSkinID(int skinID) {
        SkinID = skinID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Object getCartID() {
        return CartID;
    }

    public void setCartID(Object cartID) {
        CartID = cartID;
    }
}
