package hcmute.fit.demo.services;

import hcmute.fit.demo.models.Product;
import hcmute.fit.demo.repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(ObjectId _id) {
        return productRepository.findById(_id);
    }

    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(ObjectId _id) {
        productRepository.deleteById(_id);
    }
}
