package batch.e3.product.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import batch.e3.product.management.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);

     Optional<Product> findByPrice(double price);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

	List<Product> findByName(String name);

}
