package hcmute.fit.demo.services;

import hcmute.fit.demo.models.Skin;
import hcmute.fit.demo.repositories.SkinRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkinService {
    @Autowired
    private final SkinRepository skinRepository;

    public SkinService(SkinRepository skinRepository) {
        this.skinRepository = skinRepository;
    }

    public List<Skin> getAllSkins() {
        return skinRepository.findAll();
    }

    public Optional<Skin> getSkinById(ObjectId _id) {
        return skinRepository.findById(_id);
    }

    public Skin createOrUpdateSkin(Skin skin) {
        return skinRepository.save(skin);
    }

    public void deleteSkin(ObjectId _id) {
        skinRepository.deleteById(_id);
    }
}
