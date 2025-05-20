package hcmute.fit.demo.controllers;

import hcmute.fit.demo.models.Skin;
import hcmute.fit.demo.services.SkinService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Tag(name = "Skins", description = "Skin management APIs")
@Controller
@RequestMapping("/SkinController")
public class SkinController {

    @Autowired
    private final SkinService skinService;

    public SkinController(SkinService skinService) {
        this.skinService = skinService;
    }

    @GetMapping("/view")
    public String viewSkins(Model model) {
        model.addAttribute("skins", skinService.getAllSkins());
        return "skin_list"; // tên view (HTML/JSP) hiển thị danh sách skins
    }

    @GetMapping("/Hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("SkinController is working!", HttpStatus.OK);
    }

    @GetMapping("/GetAllSkins")
    public ResponseEntity<List<Skin>> getAllSkins() {
        return new ResponseEntity<>(skinService.getAllSkins(), HttpStatus.OK);
    }

    @GetMapping("/GetSkinById/{_id}")
    public ResponseEntity<Skin> getSkinById(@PathVariable("_id") String id) {
        ObjectId skinId = new ObjectId(id);
        Optional<Skin> skinOptional = skinService.getSkinById(skinId);

        return skinOptional
                .map(skin -> new ResponseEntity<>(skin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Create")
    public ResponseEntity<Skin> createSkin(@RequestBody Skin skin) {
        try {
            skin.set_id(new ObjectId());
            Skin created = skinService.createOrUpdateSkin(skin);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/UpdateSkin/{_id}")
    public ResponseEntity<Skin> updateSkin(
            @PathVariable("_id") String id,
            @RequestBody Skin skin) {
        try {
            ObjectId skinId = new ObjectId(id);

            if (!skinService.getSkinById(skinId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            skin.set_id(skinId);
            Skin updated = skinService.createOrUpdateSkin(skin);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteSkin/{_id}")
    public HttpStatus deleteSkin(@PathVariable("_id") String id) {
        ObjectId skinId = new ObjectId(id);

        if (skinService.getSkinById(skinId).isPresent()) {
            skinService.deleteSkin(skinId);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
