package batch.e3.product.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import batch.e3.product.management.dto.Product;
import batch.e3.product.management.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public ResponseEntity<Object> save(Product product) {
        if (repository.existsByName(product.getName())) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", "Product Name Already Exists");
            return new ResponseEntity<>(map, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            repository.save(product);
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Product Saved Successfully");
            map.put("data", product);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Object> save(List<Product> products) {
        for (Product product : products) {
            if (repository.existsByName(product.getName())) {
                Map<String, Object> map = new HashMap<>();
                map.put("error", "Product Name Already Exists");
                return new ResponseEntity<>(map, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        repository.saveAll(products);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Products Saved Successfully");
        map.put("data", products);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> fetchAll() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", "No Products Found in Database");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Products Retrieved Successfully");
            map.put("data", products);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    

    public ResponseEntity<Object> fetchByName(String name) {
          boolean productExists = repository.existsByName(name);
            if (!productExists) { 
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "No products found with name: " + name);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } else { 
            List<Product> products = repository.findByName(name); 
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "Products retrieved successfully");
            successResponse.put("data", products);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
    }


    public ResponseEntity<Object> fetchByPrice(double price) {
        Optional<Product> optional = repository.findByPrice(price);
        if (optional.isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", "No Product Found with Price: " + price);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Product Retrieved Successfully");
            map.put("data", optional.get());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

   

    public ResponseEntity<Object> delete(int id) {
        if (repository.findById(id).isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", "No Product Found with ID: " + id);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        } else {
            repository.deleteById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Product Deleted Successfully");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> update(Product product) {
        repository.save(product);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Product Updated Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ResponseEntity<Object> update(Product product, int id) {
        if (repository.findById(id).isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", "No Product Found with ID: " + id);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        } else {
            Product existingProduct = repository.findById(id).get();
            if (product.getName() != null)
                existingProduct.setName(product.getName());
            if (product.getCategory() != null)
                existingProduct.setCategory(product.getCategory());
            if (product.getPrice() != 0)
                existingProduct.setPrice(product.getPrice());
            if (product.getStock() != 0)
                existingProduct.setStock(product.getStock());

            repository.save(existingProduct);
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Product Updated Successfully");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

	
}
