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
public class Vendor {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    public Vendor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
