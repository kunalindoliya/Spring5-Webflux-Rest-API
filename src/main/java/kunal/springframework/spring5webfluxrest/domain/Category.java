package kunal.springframework.spring5webfluxrest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Kunal Indoliya.
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;
    private String description;

    public Category(String description) {
        this.description = description;
    }
}
