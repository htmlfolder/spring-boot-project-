package batch.e3.product.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import batch.e3.product.management.dto.Product;
import batch.e3.product.management.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class Productcontoller {

    @Autowired
    ProductService service;

    // Adding One Product Record
    @PostMapping("/products")
    @Operation(summary = "Adding One Product Record")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        return service.save(product);
    }

    // Adding Multiple Product Records
    @PostMapping("/products/multiple")
    @Operation(summary = "Adding Multiple Product Records")
    public ResponseEntity<Object> saveProducts(@RequestBody List<Product> products) {
        return service.save(products);
    }

    // Fetch All Records
    @GetMapping("/products")
    @Operation(summary = "Fetch All Records")
    public ResponseEntity<Object> fetchProducts() {
        return service.fetchAll();
    }

    // Fetch By Name
    @GetMapping("/products/name/{name}")
    @Operation(summary = "Fetch By Name")
    public ResponseEntity<Object> fetchByName(@PathVariable String name) {
       return service.fetchByName(name);
    }

    // fetch By Price
    @GetMapping("/products/price/{price}")
    @Operation(summary = "fetch By Price")
    public ResponseEntity<Object> fetchByPrice(@PathVariable double price) {
        return service.fetchByPrice(price);
    }

    // Delete By Id
    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete By Id")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        return service.delete(id);
    }

    // Update - PUT
    @PutMapping("/products")
    @Operation(summary = "Update - PUT")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        return service.update(product);
    }

    // Update - Patch
    @PatchMapping("/products/{id}")
    @Operation(summary = "Update - Patch")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Product product) {
        return service.update(product, id);
    }
}