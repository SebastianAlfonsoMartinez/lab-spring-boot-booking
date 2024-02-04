package ada.org.laboratoriospringboot.application.service;


import ada.org.laboratoriospringboot.domain.dto.ProductDto;
import ada.org.laboratoriospringboot.domain.entity.Product;
import ada.org.laboratoriospringboot.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record ProductService(
        ProductRepository productRepository

) {


    public void createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.name())
                .imageUrl(productDto.imageUrl())
                .description(productDto.description())
                .build();
        productRepository.save(product);
    }

    public Optional<ProductDto> findProductById(String id) {
        return productRepository.findById(id)
                .map(this::convertToProductDto);
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::convertToProductDto)
                .collect(Collectors.toList());
    }

    public boolean updateProduct(String id, ProductDto productDto) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDto.name());
            product.setImageUrl(productDto.imageUrl());
            product.setDescription(productDto.description());
            productRepository.save(product);
            return true;
        }).orElse(false);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }


    private ProductDto convertToProductDto(Product product) {
        return new ProductDto(
                product.getId(), // Asumiendo que el ID es un Integer, si no, necesitarás convertirlo
                product.getName(),
                product.getImageUrl(),
                product.getDescription());
    }


}
