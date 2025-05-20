package hcmute.fit.demo.controllers;

import hcmute.fit.demo.models.Product;
import hcmute.fit.demo.services.ProductService;
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

@Tag(name = "Products", description = "Product management APIs")
@Controller
@RequestMapping("/ProductController")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/view")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product_list";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("ProductController is working!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/GetAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/GetProductById/{_id}")
    public ResponseEntity<Product> getProductById(@PathVariable("_id") String id) {
        ObjectId productId = new ObjectId(id);
        Optional<Product> productOptional = productService.getProductById(productId);

        return productOptional
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            product.set_id(new ObjectId());
            Product created = productService.createOrUpdateProduct(product);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/UpdateProduct/{_id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("_id") String id,
            @RequestBody Product product) {
        try {
            ObjectId productId = new ObjectId(id);

            if (!productService.getProductById(productId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            product.set_id(productId);
            Product updated = productService.createOrUpdateProduct(product);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteProduct/{_id}")
    public HttpStatus deleteProduct(@PathVariable("_id") String id) {
        ObjectId productId = new ObjectId(id);

        if (productService.getProductById(productId).isPresent()) {
            productService.deleteProduct(productId);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
