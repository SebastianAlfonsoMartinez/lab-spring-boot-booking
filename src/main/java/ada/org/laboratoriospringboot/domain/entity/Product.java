package ada.org.laboratoriospringboot.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String description;

}
