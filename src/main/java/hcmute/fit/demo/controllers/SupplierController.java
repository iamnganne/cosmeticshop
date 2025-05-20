package hcmute.fit.demo.controllers;

import hcmute.fit.demo.models.Supplier;
import hcmute.fit.demo.services.SupplierService;
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

@Tag(name = "Suppliers", description = "Supplier management APIs")
@Controller
@RequestMapping("/SupplierController")
public class SupplierController {

    @Autowired
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/view")
    public String viewSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "supplier_list"; // Tên file HTML hiển thị danh sách
    }

    @GetMapping("/Hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("SupplierController is working!", HttpStatus.OK);
    }

    @GetMapping("/GetAllSuppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }

    @GetMapping("/GetSupplierById/{_id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("_id") String id) {
        ObjectId supplierId = new ObjectId(id);
        Optional<Supplier> supplierOptional = supplierService.getSupplierById(supplierId);

        return supplierOptional
                .map(supplier -> new ResponseEntity<>(supplier, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Create")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        try {
            supplier.set_id(new ObjectId());
            Supplier created = supplierService.createOrUpdateSupplier(supplier);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/UpdateSupplier/{_id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable("_id") String id,
            @RequestBody Supplier supplier) {
        try {
            ObjectId supplierId = new ObjectId(id);

            if (!supplierService.getSupplierById(supplierId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            supplier.set_id(supplierId);
            Supplier updated = supplierService.createOrUpdateSupplier(supplier);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteSupplier/{_id}")
    public HttpStatus deleteSupplier(@PathVariable("_id") String id) {
        ObjectId supplierId = new ObjectId(id);

        if (supplierService.getSupplierById(supplierId).isPresent()) {
            supplierService.deleteSupplier(supplierId);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
