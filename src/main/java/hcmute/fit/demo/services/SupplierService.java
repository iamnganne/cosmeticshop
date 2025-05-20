package hcmute.fit.demo.services;

import hcmute.fit.demo.models.Supplier;
import hcmute.fit.demo.repositories.SupplierRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(ObjectId _id) {
        return supplierRepository.findById(_id);
    }

    public Supplier createOrUpdateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(ObjectId _id) {
        supplierRepository.deleteById(_id);
    }
}
