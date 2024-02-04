package ada.org.laboratoriospringboot.domain.repository;


import ada.org.laboratoriospringboot.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> { }