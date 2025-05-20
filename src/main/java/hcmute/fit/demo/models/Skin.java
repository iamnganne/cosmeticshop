package hcmute.fit.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Skins")
public class Skin {

    @Id
    private ObjectId _id;

    private int SkinID;
    private String SkinName;
    private String Images;

    // Getters and Setters

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getSkinID() {
        return SkinID;
    }

    public void setSkinID(int skinID) {
        SkinID = skinID;
    }

    public String getSkinName() {
        return SkinName;
    }

    public void setSkinName(String skinName) {
        SkinName = skinName;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }
}
