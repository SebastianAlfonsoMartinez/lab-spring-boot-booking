package ada.org.laboratoriospringboot.application.controller;


import ada.org.laboratoriospringboot.application.service.ProductService;
import ada.org.laboratoriospringboot.domain.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public record ProductController(
        ProductService productService
) {

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id) {
        Optional<ProductDto> productDto = productService.findProductById(id);
        return productDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        boolean updated = productService.updateProduct(id, productDto);
        if(updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
